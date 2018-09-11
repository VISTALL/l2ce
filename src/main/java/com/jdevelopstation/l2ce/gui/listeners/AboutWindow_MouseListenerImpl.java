package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JWindow;

/**
 * @author VISTALL
 * @date 22:47/05.06.2011
 */
public class AboutWindow_MouseListenerImpl implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		JWindow window = (JWindow)e.getSource();
		window.setVisible(false);
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
