package com.jdevelopstation.l2ce.utils;

import java.io.File;

/**
 * @author VISTALL
 * @since 2018-05-06
 */
public interface L2CryptSupport
{
	static L2CryptSupport getInstance()
	{
		return new L2EncDec();
	}

	File decode(File f, String code);

	void encode(File in, File out, String code, int encoding);
}
