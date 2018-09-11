package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.gui.pane.DatPane;

/**
 * @author VISTALL
 * @date 2:00/06.06.2011
 */
public class DatPane_SaveButtonActionListenerImpl implements ActionListener
{
	private DatPane _datPane;

	public DatPane_SaveButtonActionListenerImpl(DatPane datPane)
	{
		_datPane = datPane;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		FileLoadInfo c = (FileLoadInfo) _datPane.getFileList().getSelectedValue();
		if(c == null)
			return;
		c.save(_datPane);
	}
}
