package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.jdevelopstation.l2ce.gui.pane.OptionPane;

/**
 * @author VISTALL
 * @date 1:07/06.06.2011
 */
public class OptionPane_OkCancelActionListenerImpl implements ActionListener
{
	private boolean _save;

	public OptionPane_OkCancelActionListenerImpl(boolean save)
	{
		_save = save;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();

		if(_save)
		{
			OptionPane pane = (OptionPane)b.getParent().getParent();
			pane.save();
		}

		// lol
		JDialog d = (JDialog)b.getParent().getParent().getParent().getParent().getParent();
		d.dispose();
	}
}
