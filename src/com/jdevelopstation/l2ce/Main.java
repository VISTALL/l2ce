package com.jdevelopstation.l2ce;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jdevelopstation.commons.logging.Log4JHelper;
import com.jdevelopstation.l2ce.data.xml.parser.ClientVersionParser;
import com.jdevelopstation.l2ce.gui.window.MainWindow;

/**
 * @author VISTALL
 * @date 8:09/18.05.2011
 */
public class Main
{
	public static void main(String... arg) throws Exception
	{
		Log4JHelper.load();
		ClientVersionParser.getInstance().load();

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		MainWindow m = MainWindow.getInstance();
		m.setVisible(true);
	}
}
