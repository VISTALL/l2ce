package com.jdevelopstation.l2ce.gui.window;

import javax.swing.JFrame;

import com.jdevelopstation.l2ce.gui.pane.MainPane;

/**
 * @author VISTALL
 * @date 8:24/18.05.2011
 */
public class MainWindow extends JFrame
{
	private static final MainWindow _instance = new MainWindow();

	public static MainWindow getInstance()
	{
		return _instance;
	}

	private MainWindow()
	{
		setContentPane(new MainPane());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 550);
		setResizable(false);
		setTitle("L2CE");
	}
}
