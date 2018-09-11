package com.jdevelopstation.l2ce.gui.pane;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jdevelopstation.commons.properties.listeners.PropertyListener;
import com.jdevelopstation.l2ce.gui.listeners.MainPane_Main_ExitActionListenerImpl;
import com.jdevelopstation.l2ce.gui.listeners.MainPane_Main_OptionsActionListenerImpl;
import com.jdevelopstation.l2ce.gui.listeners.MainPane_MemoryBar_MouseListenerImpl;
import com.jdevelopstation.l2ce.gui.listeners.MainPane_PropertyChangeListenerImpl;
import com.jdevelopstation.l2ce.gui.listeners.MainPane_Q_AboutActionListenerImpl;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
import com.jdevelopstation.l2ce.utils.BundleUtils;

/**
 * @author VISTALL
 * @date 8:33/28.05.2011
 */
public class MainPane extends JPanel
{
	private JTabbedPane _tabbedPane;
	private JPanel _panel1;
	private JMenu _mainMenu;
	private JMenu _aboutMenu;
	private JProgressBar _memoryProgressBar;
	private JLabel _versionLabel;

	public MainPane()
	{
		$$$setupUI$$$();

		_mainMenu.setText(BundleUtils.getInstance().getBundle("MainPane.Main"));

		JMenuItem item = new JMenuItem(BundleUtils.getInstance().getBundle("MainPane.Main.Options"));
		item.addActionListener(new MainPane_Main_OptionsActionListenerImpl());
		_mainMenu.add(item);

		item = new JMenuItem(BundleUtils.getInstance().getBundle("MainPane.Main.Exit"));
		item.addActionListener(new MainPane_Main_ExitActionListenerImpl());
		_mainMenu.add(item);

		_mainMenu.setText(BundleUtils.getInstance().getBundle("MainPane.Main"));

		item = new JMenuItem(BundleUtils.getInstance().getBundle("MainPane.Q.Help"));
		//item.addActionListener(new Menu_AboutActionListenerImpl());
		_aboutMenu.add(item);

		item = new JMenuItem(BundleUtils.getInstance().getBundle("MainPane.Q.About"));
		item.addActionListener(new MainPane_Q_AboutActionListenerImpl());
		_aboutMenu.add(item);

		_memoryProgressBar.addMouseListener(new MainPane_MemoryBar_MouseListenerImpl());

		PropertyListener.getInstance().addListener(GeneralProperties.class, new MainPane_PropertyChangeListenerImpl(this));
	}

	private void createUIComponents()
	{
		_panel1 = this;
	}

	public JProgressBar getMemoryProgressBar()
	{
		return _memoryProgressBar;
	}

	public JLabel getVersionLabel()
	{
		return _versionLabel;
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$()
	{
		createUIComponents();
		_panel1.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
		_tabbedPane = new JTabbedPane();
		_panel1.add(_tabbedPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		_tabbedPane.addTab("*.dat", panel1);
		final DatPane nestedForm1 = new DatPane();
		panel1.add(nestedForm1.$$$getRootComponent$$$(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		_tabbedPane.addTab("*.ini/*.int", panel2);
		final IniIntPane nestedForm2 = new IniIntPane();
		panel2.add(nestedForm2.$$$getRootComponent$$$(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
		_panel1.add(menuBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		menuBar1.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		_mainMenu = new JMenu();
		_mainMenu.setText("Main");
		menuBar1.add(_mainMenu, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		_aboutMenu = new JMenu();
		_aboutMenu.setText("?");
		menuBar1.add(_aboutMenu, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
		_panel1.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null));
		_memoryProgressBar = new JProgressBar();
		_memoryProgressBar.setBorderPainted(false);
		_memoryProgressBar.setStringPainted(true);
		panel3.add(_memoryProgressBar, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(110, 17), new Dimension(110, 17), new Dimension(110, 17), 0, false));
		final Spacer spacer2 = new Spacer();
		panel3.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		_versionLabel = new JLabel();
		_versionLabel.setText("NONE");
		panel3.add(_versionLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$()
	{
		return _panel1;
	}
}