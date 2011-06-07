package com.jdevelopstation.l2ce.gui.listeners;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import com.jdevelopstation.commons.properties.listeners.PropertyChangeListener;
import com.jdevelopstation.commons.properties.listeners.PropertyEvent;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;
import com.jdevelopstation.l2ce.gui.pane.DatPane;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;

/**
 * @author VISTALL
 * @date 1:38/06.06.2011
 */
public class DatPane_PropertyChangeListenerImpl implements PropertyChangeListener
{
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
			return;
		File dir = new File(GeneralProperties.WORKING_DIRECTORY);
		if(!dir.exists())
			return;

		Set<FileLoadInfo> s = new TreeSet<FileLoadInfo>();
		for(ClientFile cf : v.getClientFiles())
		for(File f : dir.listFiles())
			if(cf.getPattern().matcher(f.getName()).find())
				s.add(new FileLoadInfo(cf, f));

		_datPane.getFileList().setListData(s.toArray(new FileLoadInfo[s.size()]));
	}
}
