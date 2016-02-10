package com.netpong;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NetPongSetupPanel extends Panel implements KeyListener
{
	public String ipaddr = "";
	public NetPongSetupPanel()
	{
		addKeyListener(this);
	}
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawString("Hostname or IP address of Pong Server:", 12, 12);
		g.drawString(ipaddr, 12, 26);
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			ipaddr = ipaddr.substring(0, ipaddr.length() - 1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			Frame parentFrame = (Frame)getParent();
			parentFrame.remove(this);
			parentFrame.add(new NetPongPanel(640, 480, ipaddr));
			parentFrame.setVisible(false);
			parentFrame.setVisible(true);
		}
		else
		{
			ipaddr += e.getKeyChar();
		}
		e.consume();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
}
