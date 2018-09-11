package com.jdevelopstation.l2ce.gui.renders;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.jdevelopstation.l2ce.gui.etc.FileLoadInfo;

/**
 * @author VISTALL
 * @date 1:50/29.05.2011
 */
public class DatPane_FileListCellRenderer extends DefaultListCellRenderer
{
	private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
	private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
	protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		setComponentOrientation(list.getComponentOrientation());

		FileLoadInfo fileLoadInfo = (FileLoadInfo)value;


		JList.DropLocation dropLocation = list.getDropLocation();
		if(dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index)
			isSelected = true;

		if(isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(fileLoadInfo.isDisabled() ? Color.GRAY : list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(fileLoadInfo.isDisabled() ? Color.GRAY : list.getForeground());
		}

		setText(value.toString());

		setEnabled(list.isEnabled());
		setFont(list.getFont());

		Border border = null;
		if(cellHasFocus)
		{
			if(isSelected)
			{
				border = UIManager.getDefaults().getBorder("List.focusSelectedCellHighlightBorder");
			}
			if(border == null)
			{
				border = UIManager.getDefaults().getBorder("List.focusCellHighlightBorder");
			}
		}
		else
		{
			border = getNoFocusBorder();
		}
		setBorder(border);

		return this;
	}

	private Border getNoFocusBorder()
	{
		Border border = UIManager.getDefaults().getBorder("List.cellNoFocusBorder");
		if(System.getSecurityManager() != null)
		{
			if(border != null)
				return border;
			return SAFE_NO_FOCUS_BORDER;
		}
		else
		{
			if(border != null && (noFocusBorder == null || noFocusBorder == DEFAULT_NO_FOCUS_BORDER))
			{
				return border;
			}
			return noFocusBorder;
		}
	}
}
