package com.jdevelopstation.l2ce.gui.tasks;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

import javax.swing.JProgressBar;

import com.jdevelopstation.l2ce.utils.BundleUtils;
import com.jdevelopstation.l2ce.utils.RunnableImpl;

/**
 * @author VISTALL
 * @date 23:23/05.06.2011
 */
public class MemoryBarUpdateTask extends RunnableImpl
{
	private JProgressBar _progressBar;

	public MemoryBarUpdateTask(JProgressBar progressBar)
	{
		_progressBar = progressBar;
	}

	@Override
	protected void runImpl() throws Throwable
	{
		MemoryUsage hm = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

		long use = hm.getUsed() / 1048576;
		long max = hm.getMax() / 1048576;
		byte persents = (byte) ((use * 100) / max);

		_progressBar.setString(BundleUtils.getInstance().getBundle("MainPane.MemoryProgressBar.Text", use, max));
		_progressBar.setValue(persents);
		_progressBar.setToolTipText((BundleUtils.getInstance().getBundle("MainPane.MemoryProgressBar.ToolTipText", max, use)));
	}
}
