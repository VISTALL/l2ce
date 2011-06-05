package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.jdevelopstation.l2ce.gui.pane.OptionPane;
import com.jdevelopstation.l2ce.gui.window.MainFrame;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
import com.jdevelopstation.l2ce.utils.RunnableImpl;

/**
 * @author VISTALL
 * @date 0:42/06.06.2011
 */
public class OptionPane_SelectDirActionListenerImpl implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		final JFileChooser chooser = new JFileChooser(GeneralProperties.WORKING_DIRECTORY);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		JButton b = (JButton)e.getSource();

		final OptionPane pane = (OptionPane)b.getParent().getParent().getParent().getParent();

		EventQueue.invokeLater(new RunnableImpl()
		{
			@Override
			protected void runImpl() throws Throwable
			{
				if(chooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)
					pane.setTempDir(chooser.getSelectedFile().getAbsolutePath());
			}
		});
	}
}
