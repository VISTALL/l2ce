package com.jdevelopstation.commons.properties.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VISTALL
 * @date 1:30/06.06.2011
 */
public class PropertyListener
{
	private static final PropertyListener _instance = new PropertyListener();

	private Map<Class, List<PropertyChangeListener>> _listMap = new HashMap<Class, List<PropertyChangeListener>>();

	public static PropertyListener getInstance()
	{
		return _instance;
	}

	public void addListener(Class clazz, PropertyChangeListener listener)
	{
		List<PropertyChangeListener> listeners = _listMap.get(clazz);
		if(listeners == null)
			_listMap.put(clazz, listeners = new ArrayList<PropertyChangeListener>());

		listeners.add(listener);
	}

	public void firePropertyChanged(Class clazz)
	{
		List<PropertyChangeListener> listeners = _listMap.get(clazz);
		if(listeners == null)
			return;
		PropertyEvent e = new PropertyEvent(clazz);
		for(PropertyChangeListener listener : listeners)
			listener.propertyChanged(e);
	}
}
