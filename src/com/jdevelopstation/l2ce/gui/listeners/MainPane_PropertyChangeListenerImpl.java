package com.jdevelopstation.l2ce.gui.listeners;

import org.apache.commons.lang.StringUtils;
import com.jdevelopstation.commons.properties.listeners.PropertyChangeListener;
import com.jdevelopstation.commons.properties.listeners.PropertyEvent;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.gui.pane.MainPane;
import com.jdevelopstation.l2ce.version.ClientVersion;

/**
 * @author VISTALL
 * @date 1:35/06.06.2011
 */
public class MainPane_PropertyChangeListenerImpl implements PropertyChangeListener
{
	private MainPane _mainPane;

	public MainPane_PropertyChangeListenerImpl(MainPane mainPane)
	{
		_mainPane = mainPane;
	}

	@Override
	public void propertyChanged(PropertyEvent e)
	{
		ClientVersion v = ClientVersionHolder.getInstance().getCurrentVersion();

		_mainPane.getVersionLabel().setText(v == null ? StringUtils.EMPTY : v.getName());
	}
}
