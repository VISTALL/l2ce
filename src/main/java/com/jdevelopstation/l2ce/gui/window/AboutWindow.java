package com.jdevelopstation.l2ce.gui.window;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JWindow;

import com.jdevelopstation.l2ce.gui.listeners.AboutWindow_MouseListenerImpl;
import com.jdevelopstation.l2ce.gui.pane.AboutPane;

/**
 * @author VISTALL
 * @date 22:28/05.06.2011
 */
public class AboutWindow extends JWindow
{
	public AboutWindow(Frame owner)
	{
		super(owner);

		setBackground(Color.GRAY);
		setContentPane(new AboutPane());
		setSize(400, 100);
		setLocationRelativeTo(owner);
		addMouseListener(new AboutWindow_MouseListenerImpl());
	}
}
