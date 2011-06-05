package com.jdevelopstation.l2ce;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jdevelopstation.commons.logging.Log4JHelper;
import com.jdevelopstation.commons.properties.listeners.PropertyListener;
import com.jdevelopstation.l2ce.data.xml.parser.ClientVersionParser;
import com.jdevelopstation.l2ce.gui.window.MainFrame;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
import com.jdevelopstation.l2ce.utils.BundleUtils;
import com.jdevelopstation.l2ce.utils.PropertiesUtils;
import com.jdevelopstation.l2ce.utils.ThreadPoolManager;

/**
 * @author VISTALL
 * @date 8:09/18.05.2011
 */
public class Main
{
	public static void main(String... arg) throws Exception
	{
		Log4JHelper.load();
		BundleUtils.getInstance();
		PropertiesUtils.getInstance();
		ThreadPoolManager.getInstance();
		ClientVersionParser.getInstance().load();
		PropertiesUtils.getInstance().load();

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		MainFrame m = MainFrame.getInstance();
		m.setVisible(true);

		PropertyListener.getInstance().firePropertyChanged(GeneralProperties.class);
	}
}
