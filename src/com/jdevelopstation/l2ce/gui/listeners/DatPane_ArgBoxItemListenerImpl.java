package com.jdevelopstation.l2ce.gui.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.jdevelopstation.l2ce.gui.pane.DatPane;
import com.jdevelopstation.l2ce.properties.GeneralProperties;

/**
 * @author VISTALL
 * @date 8:36/07.06.2011
 */
public class DatPane_ArgBoxItemListenerImpl implements ItemListener
{
	private DatPane _datPane;

	public DatPane_ArgBoxItemListenerImpl(DatPane datPane)
	{
		_datPane = datPane;
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		GeneralProperties.DAT_ARGUMENT = (String)_datPane.getArgBox().getSelectedItem();
	}
}
