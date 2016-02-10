package com.netpong;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;

public class NetPongPanel extends Panel implements KeyListener, Runnable
{
	BufferedImage backBuffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	Selector sel;
	SocketChannel clientSocket;
	
	LinkedList<Integer> clientQueue = new LinkedList<>();
	
	public NetPongPanel(int w, int h, String ipaddr)
	{
		try
		{
			sel = Selector.open();
			clientSocket = SocketChannel.open(new InetSocketAddress(ipaddr, 7600));
			Thread networkThread = new Thread(this);
			networkThread.start();
		}
		catch(IOException e)
		{
			System.err.println("I/O Exception");
			System.exit(2);
		}
	}
	public void update(Graphics g)
	{
		g.drawImage(backBuffer, 0, 0, this);
	}
	public void paint(Graphics g)
	{
		update(g);
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			
			break;
		case KeyEvent.VK_DOWN:
			
			break;
		}
		
		e.consume();
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		
		e.consume();
	}
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				sel.select();
				Iterator<SelectionKey> selectedKeys = sel.selectedKeys().iterator();
				
				while(selectedKeys.hasNext())
				{
					SelectionKey key = selectedKeys.next();
					
					if(key.isReadable())
					{
						
					}
					else if(key.isWritable())
					{
						
					}
				}
			}
			catch(IOException e)
			{
				
			}
		}
	}
}
