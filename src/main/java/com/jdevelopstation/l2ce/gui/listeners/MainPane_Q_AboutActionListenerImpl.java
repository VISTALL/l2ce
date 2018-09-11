package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jdevelopstation.l2ce.gui.window.AboutWindow;
import com.jdevelopstation.l2ce.gui.window.MainFrame;
import com.jdevelopstation.l2ce.utils.RunnableImpl;

/**
 * @author VISTALL
 * @date 22:45/05.06.2011
 */
public class MainPane_Q_AboutActionListenerImpl implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		EventQueue.invokeLater(new RunnableImpl()
		{
			@Override
			protected void runImpl() throws Throwable
			{
				AboutWindow w = new AboutWindow(MainFrame.getInstance());
				w.setVisible(true);
			}
		});
	}
}
