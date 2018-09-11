package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.jdevelopstation.l2ce.utils.PropertiesUtils;
import com.jdevelopstation.l2ce.utils.ThreadPoolManager;

/**
 * @author VISTALL
 * @date 19:38/01.06.2011
 */
public class MainFrame_WindowListenerImpl implements WindowListener
{
	@Override
	public void windowOpened(WindowEvent e)
	{

	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		PropertiesUtils.getInstance().save();
		ThreadPoolManager.getInstance().shutdown();
	}

	@Override
	public void windowClosed(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{

	}

	@Override
	public void windowActivated(WindowEvent e)
	{

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{

	}
}
