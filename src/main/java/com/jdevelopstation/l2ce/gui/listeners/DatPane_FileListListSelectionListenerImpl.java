package com.jdevelopstation.l2ce.gui.listeners;

import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.gui.pane.DatPane;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author VISTALL
 * @date 1:54/06.06.2011
 */
public class DatPane_FileListListSelectionListenerImpl implements ListSelectionListener
{
	private DatPane _datPane;

	public DatPane_FileListListSelectionListenerImpl(DatPane datPane)
	{
		_datPane = datPane;
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		FileLoadInfo f = (FileLoadInfo)_datPane.getFileList().getSelectedValue();
		if(f == null)
		{
			_datPane.getLoadButton().setEnabled(false);
			_datPane.getExportButton().setEnabled(false);
			_datPane.getImportButton().setEnabled(false);
			_datPane.getSaveButton().setEnabled(false);
			_datPane.getSaveNoCrypt().setEnabled(false);
			_datPane.getClearButton().setEnabled(false);
		}
		else
		{
			_datPane.getLoadButton().setEnabled(true);
			_datPane.getImportButton().setEnabled(true);

			_datPane.getClearButton().setEnabled(f.getDataSize() != -1);
			_datPane.getExportButton().setEnabled(f.getDataSize() != -1);

			_datPane.getSaveButton().setEnabled(f.getDataSize() != -1);
			_datPane.getSaveNoCrypt().setEnabled(f.getDataSize() != -1);
			_datPane.getModifyButton().setEnabled(f.getDataSize() != -1);
		}
	}
}
