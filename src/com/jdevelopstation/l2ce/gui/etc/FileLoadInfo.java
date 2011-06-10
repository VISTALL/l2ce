package com.jdevelopstation.l2ce.gui.etc;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.jdevelopstation.l2ce.data.xml.holder.ClientVersionHolder;
import com.jdevelopstation.l2ce.gui.pane.DatPane;
import com.jdevelopstation.l2ce.gui.tasks.ListRepaintTask;
import com.jdevelopstation.l2ce.gui.window.MainFrame;
import com.jdevelopstation.l2ce.properties.GeneralProperties;
import com.jdevelopstation.l2ce.utils.L2EncDec;
import com.jdevelopstation.l2ce.utils.RunnableImpl;
import com.jdevelopstation.l2ce.utils.ThreadPoolManager;
import com.jdevelopstation.l2ce.version.ClientVersion;
import com.jdevelopstation.l2ce.version.node.data.ClientData;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;

/**
* @author VISTALL
* @date 1:36/29.05.2011
*/
public class FileLoadInfo implements Comparable<FileLoadInfo>
{
	private static final FileFilter XML_EXPORT = new ExtensionFileFilter("XML Export", new String[] { "XML" });
	private static final FileFilter CSV_EXPORT = new ExtensionFileFilter("CSV Export", new String[] { "CSV" });
	private static final FileFilter TSV_EXPORT = new ExtensionFileFilter("TSV Export", new String[] { "TSV" });

	private final ClientFile _clientFile;
	private final File _file;
	private ClientData _clientData;

	private boolean _disabled;

	public FileLoadInfo(ClientFile c, File file)
	{
		_clientFile = c;
		_file = file;
	}

	@Override
	public String toString()
	{
		long count = 0;
		if(_clientData != null)
		{
			ClientDataNode node = _clientData.getNodeByName("data");
			if(node != null)
				count = ((Number) node.getValue()).longValue();
		}
		return _file.getName() + " [" + count + "]";
	}

	public long getDataSize()
	{
		long count = -1;
		if(_clientData != null)
		{
			ClientDataNode node = _clientData.getNodeByName("data");
			if(node != null)
				count = ((Number) node.getValue()).longValue();
		}
		return count;
	}

	@Override
	public int compareTo(FileLoadInfo o)
	{
		return toString().compareTo(o.toString());
	}

	public ClientFile getClientFile()
	{
		return _clientFile;
	}

	public File getFile()
	{
		return _file;
	}

	public ClientData getClientData()
	{
		return _clientData;
	}

	public void load(final String arg, final DatPane dat)
	{
		if(isDisabled())
			return;

		setDisabled(true);

		ThreadPoolManager.getInstance().execute(new RunnableImpl()
		{
			@Override
			protected void runImpl() throws Throwable
			{
				try
				{
					final File f = L2EncDec.decode(_file, arg);
					if(f != null)
						_clientData = _clientFile.parse(f);
				}
				finally
				{
					setDisabled(false);

					EventQueue.invokeLater(new ListRepaintTask(dat.getFileList()));
				}
			}
		});
	}

	public void export(final DatPane dat)
	{
		@SuppressWarnings("serial")
		final JFileChooser chooser = new JFileChooser(GeneralProperties.LAST_EXPORT_DIRECTORY)
		{
			@Override
			public void approveSelection()
			{
				File f = getSelectedFile();
				//if file not accepted, missing extension
				if (!getFileFilter().accept(f))
				{
					f = new File(f.getAbsolutePath()+"."+((ExtensionFileFilter)getFileFilter()).getFirstExtension());
				}
				if (f.exists())
				{
					int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (result)
					{
						case JOptionPane.YES_OPTION:
							super.approveSelection();
							return;
						case JOptionPane.NO_OPTION:
							return;
						case JOptionPane.CANCEL_OPTION:
							cancelSelection();
							return;
					}
				}
				super.approveSelection();
			}
		};
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setDialogTitle("Save to file");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setSelectedFile(new File(_file.getName().replace(".dat", "")));
		chooser.addChoosableFileFilter(XML_EXPORT);
		chooser.addChoosableFileFilter(CSV_EXPORT);
		chooser.addChoosableFileFilter(TSV_EXPORT);
		chooser.setFileFilter(XML_EXPORT);
		EventQueue.invokeLater(new RunnableImpl()
		{
			@Override
			protected void runImpl() throws Throwable
			{
				if(chooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)
				{
					File f = chooser.getSelectedFile();

					GeneralProperties.LAST_EXPORT_DIRECTORY = f.getParent();

					String saveFile = f.getAbsolutePath();

					//if file not accepted, missing extension
					if (!chooser.getFileFilter().accept(f))
						saveFile += "."+((ExtensionFileFilter) chooser.getFileFilter()).getFirstExtension();

					if (chooser.getFileFilter() == XML_EXPORT)
						_clientData.toXML(saveFile);
					else if (chooser.getFileFilter() == CSV_EXPORT)
					{
						//TODO: csv save
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "CSV not supported yet");
					}
					else if (chooser.getFileFilter() == CSV_EXPORT)
					{
						//TODO: tsv save
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "TSV not supported yet");
					}
				}
			}
		});
	}

	public void importFromXML(final DatPane datPane)
	{
		final ClientVersion v = ClientVersionHolder.getInstance().getCurrentVersion();
		if(v == null)
			return;

		final JFileChooser chooser = new JFileChooser(GeneralProperties.LAST_IMPORT_DIRECTORY);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setDialogTitle("Import from file");

		EventQueue.invokeLater(new RunnableImpl()
		{
			@Override
			protected void runImpl() throws Throwable
			{
				if(chooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)
				{
					File f = chooser.getSelectedFile();

					GeneralProperties.LAST_IMPORT_DIRECTORY = f.getParent();

					_clientData = new ClientData();
					_clientData.fromXML(v, f.getAbsolutePath());

					EventQueue.invokeLater(new ListRepaintTask(datPane.getFileList()));
				}
			}
		});
	}

	public void clear(final DatPane datPane)
	{
		_clientData = null;
		EventQueue.invokeLater(new ListRepaintTask(datPane.getFileList()));
	}

	public boolean isDisabled()
	{
		return _disabled;
	}

	public void setDisabled(boolean disabled)
	{
		_disabled = disabled;
	}
}
