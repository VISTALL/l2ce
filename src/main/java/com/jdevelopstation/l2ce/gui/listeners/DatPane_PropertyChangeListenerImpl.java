package com.jdevelopstation.l2ce.gui.listeners;

import com.jdevelopstation.commons.properties.listeners.PropertyChangeListener;
import com.jdevelopstation.commons.properties.listeners.PropertyEvent;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.gui.pane.DatPane;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author VISTALL
 * @date 1:38/06.06.2011
 */
public class DatPane_PropertyChangeListenerImpl implements PropertyChangeListener
{
	private static class FileListComparator implements Comparator<FileLoadInfo>
	{
		@Override
		public int compare(FileLoadInfo o1, FileLoadInfo o2)
		{
			return o1.getFile().getName().compareToIgnoreCase(o2.getFile().getName());
		}
	}

	private DatPane _datPane;

	public DatPane_PropertyChangeListenerImpl(DatPane datPane)
	{
		_datPane = datPane;
	}

	@Override
	public void propertyChanged(PropertyEvent e)
	{
		_datPane.getFileList().setListData(ArrayUtils.EMPTY_OBJECT_ARRAY);
		_datPane.getArgBox().setSelectedItem(GeneralProperties.DAT_ARGUMENT);

		ClientVersion v = ClientVersionHolder.getInstance().getCurrentVersion();
		if(v == null)
		{
			return;
		}
		File dir = new File(GeneralProperties.WORKING_DIRECTORY);
		if(!dir.exists())
		{
			return;
		}

		Set<FileLoadInfo> fileLoadInfos = new TreeSet<>(new FileListComparator());
		for(ClientFile cf : v.getClientFiles())
		{
			for(File f : dir.listFiles())
			{
				if(cf.match(f.getName()))
				{
					fileLoadInfos.add(new FileLoadInfo(cf, f, fileLoadInfos));
				}
			}
		}

		_datPane.getFileList().setListData(fileLoadInfos.toArray(new FileLoadInfo[fileLoadInfos.size()]));
	}
}
