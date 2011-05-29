package com.jdevelopstation.l2ce.gui.pane.actions;

import java.io.File;

import com.jdevelopstation.l2ce.gui.pane.DatPane;
import com.jdevelopstation.l2ce.utils.L2EncDec;
import com.jdevelopstation.l2ce.utils.RunnableImpl;
import com.jdevelopstation.l2ce.utils.ThreadPoolManager;
import com.jdevelopstation.l2ce.version.node.data.ClientData;
import com.jdevelopstation.l2ce.version.node.data.ClientDataNode;
import com.jdevelopstation.l2ce.version.node.file.ClientFile;

/**
* @author VISTALL
* @date 1:36/29.05.2011
*/
public class FileLoadInfo implements Comparable<FileLoadInfo>
{
	private final ClientFile _clientFile;
	private final File _file;
	private ClientData _clientData;

	private boolean _disabled;

	public FileLoadInfo(ClientFile c, File file)
	{
		_clientFile = c;
		_file = file;
	}

	public void setClientData(ClientData clientData)
	{
		_clientData = clientData;
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

	public void load(final DatPane dat)
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
					final File f = L2EncDec.decode(_file, "-s");
					if(f != null)
						_clientData = _clientFile.parse(f);
				}
				finally
				{
					setDisabled(false);

					dat.repaintList();
				}
			}
		});
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
