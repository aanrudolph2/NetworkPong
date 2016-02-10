package com.netpong;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Server implements Runnable
{
	private volatile boolean started = true;
	
	private Selector selector;
	private ServerSocketChannel serverChannel;
	
	HashMap<SelectionKey, Queue<Object>> clientOps = new HashMap<>();
	
	public static void main(String[] args)
	{
		Thread serverThread;
		try
		{
			serverThread = new Thread(new Server(7600));
			serverThread.start();
		}
		catch (IOException e)
		{
			System.out.println("Failed to initialize. Exiting.");
		}
	}
	
	public Server(int port) throws IOException
	{
		selector = Selector.open();
		serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));

		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	

	@Override
	public void run()
	{
		while(started)
		{
			try
			{
				selector.select();
				
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				
				while(keys.hasNext())
				{
					SelectionKey key = keys.next();
					
					keys.remove();
					
					if(key.isValid())
					{
						if(key.isAcceptable())
						{
							// Accept
							SocketChannel client = ((ServerSocketChannel) key.channel()).accept();
							client.configureBlocking(false);
							client.register(selector, SelectionKey.OP_READ);
							clientOps.put(client.keyFor(selector), new LinkedList<Object>());
						}
						else if(key.isWritable())
						{
							// Write
							SocketChannel chan = (SocketChannel) key.channel();
							
							Queue<Object> tmp = clientOps.get(key);
							byte[] ctrl = new byte[4];
							
							while((ctrl = (byte[]) tmp.poll()) != null)
							{
								ByteBuffer output = ByteBuffer.wrap(ctrl);
								chan.write(output);
							}
							chan.register(selector, SelectionKey.OP_READ);
						}
						else if(key.isReadable())
						{
							// Read
							SocketChannel chan = (SocketChannel) key.channel();
							try
							{
								byte[] ctrl = new byte[4];
								ByteBuffer input = ByteBuffer.wrap(ctrl);
								// Read in control word
								chan.read(input);
								if(!clientOps.containsKey(key))
								{
									System.out.println("Adding new key");
									clientOps.put(key, new LinkedList<Object>());
								}
								// clientOps.get(key).add(ctrl);
								
								Iterator<SelectionKey> clientKeys = clientOps.keySet().iterator();
								
								while(clientKeys.hasNext())
								{
									SelectionKey tmpKey = clientKeys.next();
									
									if(tmpKey.isValid())
									{
										if(!tmpKey.equals(key))
										{
											tmpKey.interestOps(SelectionKey.OP_WRITE);
											clientOps.get(tmpKey).add(ctrl);
										}
									}
									else
									{
										
									}
								}
							}
							catch(IOException e)
							{
								clientOps.keySet().remove(chan.keyFor(selector));
								chan.close();
							}
						}
					}
				}
			}
			catch (IOException e)
			{
				
			}
		}
	}
}
