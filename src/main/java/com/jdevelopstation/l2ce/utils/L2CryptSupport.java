package com.jdevelopstation.l2ce.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

/**
 * @author VISTALL
 * @since 2018-05-06
 */
public interface L2CryptSupport
{
	static L2CryptSupport getInstance()
	{
		if(Boolean.FALSE)
		{
			return new L2CryptBuildIn();
		}
		return new L2EncDec();
	}

	Pair<String, byte[]> decode(File f, String code);

	void encode(File in, File out, String code, int encoding) throws Exception;
}
