package com.jdevelopstation.l2ce.gui.pane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.jdevelopstation.l2ce.Config;
import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.gui.window.MainWindow;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;

/**
 * @author VISTALL
 * @date 8:20/18.05.2011
 */
public class DatPane extends JPanel
{
	private JList _fileList;
	private JPanel _contentPane;
	private JButton _button1;
	private JButton _button2;
	private JComboBox _versionList;
	private JButton _updateFiles;
	private JButton _selectDir;
	private JLabel _dirName;

	public DatPane()
	{
		for(String v : ClientVersionHolder.getInstance().getVersionMap().keySet())
			_versionList.addItem(v);

		_updateFiles.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object o = _versionList.getSelectedItem();
				if(o == null)
					return;
				ClientVersion v = ClientVersionHolder.getInstance().getVersion((String)o);
				File[] files = new File(Config.DIRECTORY).listFiles();

				Set<String> s = new TreeSet<String> ();
				for(ClientFile cf: v.getClientFiles())
				{
					for(File f : files)
					{
						if(cf.getPattern().matcher(f.getName()).find())
							s.add(f.getName());
					}
				}
				_fileList.setListData(s.toArray(new Object[s.size()]));
			}
		});

		_selectDir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JFileChooser chooser = new JFileChooser();  //TODO [VISTALL] last folder
				final int returnVal = chooser.showOpenDialog(MainWindow.getInstance());

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{

				}
			}
		});
	}

	private void createUIComponents()
	{
		_contentPane = this;
	}
}
