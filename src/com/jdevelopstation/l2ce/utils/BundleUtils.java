package com.jdevelopstation.l2ce.utils;

import java.util.ResourceBundle;

/**
 * @author VISTALL
 * @date 20:12/01.06.2011
 */
public class BundleUtils
{
	private static final BundleUtils _instance = new BundleUtils();

	private ResourceBundle _bundle;

	public static BundleUtils getInstance()
	{
		return _instance;
	}

	public BundleUtils()
	{
		//Locale.setDefault(Locale.ENGLISH);

		_bundle = ResourceBundle.getBundle("com/jdevelopstation/l2ce/strings/Bundle");
	}

	public String getBundle(String text, Object... ar)
	{
		return String.format(_bundle.getString(text), ar);
	}
}
