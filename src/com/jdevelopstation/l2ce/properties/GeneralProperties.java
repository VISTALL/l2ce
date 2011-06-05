package com.jdevelopstation.l2ce.properties;

import com.jdevelopstation.commons.properties.ann.Property;
import com.jdevelopstation.commons.properties.ann.PropertyRoot;

/**
 * @author VISTALL
 * @date 23:53/05.06.2011
 */
@PropertyRoot("general")
public class GeneralProperties
{
	@Property(value = "working_directory", defValue = ".")
	public static String WORKING_DIRECTORY;

	@Property(value = "current_version")
	public static String CURRENT_VERSION;
}
