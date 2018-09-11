package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author VISTALL
 * @date 23:38/05.06.2011
 */
public class MainPane_MemoryBar_MouseListenerImpl implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			System.gc();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}
}
