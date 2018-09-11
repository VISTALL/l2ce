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

	@Property(value = "last_export_directory", defValue = ".")
	public static String LAST_EXPORT_DIRECTORY;

	@Property(value = "last_import_directory", defValue = ".")
	public static String LAST_IMPORT_DIRECTORY;

	@Property(value = "current_version")
	public static String CURRENT_VERSION;

	@Property(value = "dat_argument", defValue = "-s")
	public static String DAT_ARGUMENT;

	@Property(value = "make_backup_on_save", defValue = "true")
	public static boolean MAKE_BACKUP_ON_SAVE;

	@Property(value = "save_without_dialog", defValue = "true")
	public static boolean SAVE_WITHOUT_DIALOG;
}
