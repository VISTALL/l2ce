package com.jdevelopstation.l2ce.gui.window;

import java.awt.Frame;

import javax.swing.JDialog;

import com.jdevelopstation.l2ce.gui.pane.OptionPane;
import com.jdevelopstation.l2ce.utils.BundleUtils;

/**
 * @author VISTALL
 * @date 21:52/05.06.2011
 */
public class OptionDialog extends JDialog
{
	public OptionDialog(Frame owner)
	{
		super(owner);
		setContentPane(new OptionPane());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 350);
		setResizable(false);
		setLocationRelativeTo(owner);
		setTitle(BundleUtils.getInstance().getBundle("OptionDialog.Title"));
	}
}
