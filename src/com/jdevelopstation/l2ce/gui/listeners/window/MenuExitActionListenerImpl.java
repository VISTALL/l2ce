package com.jdevelopstation.l2ce.gui.listeners.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author VISTALL
 * @date 20:28/01.06.2011
 */
public class MenuExitActionListenerImpl implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}
}
