package com.jdevelopstation.l2ce.gui.window;

import javax.swing.JFrame;

import com.jdevelopstation.l2ce.gui.listeners.MainFrame_WindowListenerImpl;
import com.jdevelopstation.l2ce.gui.pane.MainPane;
import com.jdevelopstation.l2ce.gui.tasks.MemoryBarUpdateTask;
import com.jdevelopstation.l2ce.utils.BundleUtils;
import com.jdevelopstation.l2ce.utils.ThreadPoolManager;

/**
 * @author VISTALL
 * @date 8:24/18.05.2011
 */
public class MainFrame extends JFrame
{
	private static final MainFrame _instance = new MainFrame();

	public static MainFrame getInstance()
	{
		return _instance;
	}

	private MainFrame()
	{
		setContentPane(new MainPane());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 550);
		setResizable(false);
		setTitle(String.format("%s [%s]", BundleUtils.getInstance().getBundle("MainFrame.Title"), BundleUtils.getInstance().getBundle("AboutPane.Version")));
		setLocationRelativeTo(null);

		addWindowListener(new MainFrame_WindowListenerImpl());
	}

	@Override
	public void setVisible(boolean vis)
	{
		super.setVisible(vis);
		if(vis)
			ThreadPoolManager.getInstance().scheduleAtFixedRate(new MemoryBarUpdateTask(((MainPane)getContentPane()).getMemoryProgressBar()), 0, 1000L);
	}
}
