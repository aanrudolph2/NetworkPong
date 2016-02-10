package com.netpong;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NetPongFrame extends Frame
{
	public NetPongFrame()
	{
		NetPongSetupPanel pongPanel = new NetPongSetupPanel();
		setSize(640, 480);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		add(pongPanel);
	}
	public static void main(String[] args)
	{
		NetPongFrame pong = new NetPongFrame();
		pong.setVisible(true);
	}
}
