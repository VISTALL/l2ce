package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jdevelopstation.l2ce.gui.window.MainFrame;
import com.jdevelopstation.l2ce.gui.window.OptionDialog;
import com.jdevelopstation.l2ce.utils.RunnableImpl;

/**
 * @author VISTALL
 * @date 22:36/01.06.2011
 */
public class MainPane_Main_OptionsActionListenerImpl implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		EventQueue.invokeLater(new RunnableImpl()
		{
			@Override
			protected void runImpl() throws Throwable
			{
				OptionDialog dialog = new OptionDialog(MainFrame.getInstance());
				dialog.setVisible(true);
			}
		});
	}
}
