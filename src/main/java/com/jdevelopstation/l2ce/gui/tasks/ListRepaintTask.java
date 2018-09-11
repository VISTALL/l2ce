package com.jdevelopstation.l2ce.gui.tasks;

import javax.swing.JList;

import com.jdevelopstation.l2ce.utils.RunnableImpl;

/**
 * @author VISTALL
 * @date 1:37/29.05.2011
 */
public class ListRepaintTask extends RunnableImpl
{
	private final JList _jList;

	public ListRepaintTask(JList jList)
	{
		_jList = jList;
	}

	@Override
	public void runImpl()
	{
		_jList.getListSelectionListeners()[0].valueChanged(null);
		_jList.setSelectedValue(_jList.getSelectedValue(), true);
		_jList.invalidate();
	}
}
