package com.jdevelopstation.commons.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import com.jdevelopstation.commons.properties.ann.Property;
import com.jdevelopstation.commons.properties.ann.PropertyRoot;

/**
 * @author VISTALL
 * @date 23:52/05.06.2011
 */
public class PropertyInOut
{
	private static final Logger _log = Logger.getLogger(PropertyInOut.class);
	private Map<Class<?>, PropertyRootImpl> _mapping = new HashMap<Class<?>, PropertyRootImpl>();

	public void read(String file)
	{
		for(Map.Entry<Class<?>, PropertyRootImpl> entry : _mapping.entrySet())
			initDef(entry.getValue());

		File f = new File(file);
		if(f.exists())
		{
			try
			{
				SAXReader reader = new SAXReader();
				reader.setValidation(false);

				Document doc = reader.read(f);
				for(Map.Entry<Class<?>, PropertyRootImpl> entry : _mapping.entrySet())
					read(doc.getRootElement(), entry.getValue());
			}
			catch(DocumentException e)
			{
				_log.error("Exception: " + e, e);
			}
		}
	}

	private void read(Element e, PropertyRootImpl propertyRoot)
	{
		Element dirElement = e.element(propertyRoot.getName());
		if(dirElement == null)
			return;

		for(Map.Entry<Property, Field> entry : propertyRoot.getFieldMap().entrySet())
		{
			Element proElement = dirElement.element(entry.getKey().value());
			if(proElement != null)
			{
				Field field = entry.getValue();

				try
				{
					field.set(null, ObjectParse.parse(field.getType(), proElement.getText()));
				}
				catch(IllegalAccessException e1)
				{
					_log.error("Exception: " + e1, e1);
				}
			}
		}
	}

	private void initDef(PropertyRootImpl propertyRoot)
	{
		for(Map.Entry<Property, Field> entry2 : propertyRoot.getFieldMap().entrySet())
		{
			String defValue = entry2.getKey().defValue();
			if(!StringUtils.isEmpty(defValue))
			{
				Field field = entry2.getValue();

				try
				{
					field.set(null, ObjectParse.parse(field.getType(), entry2.getKey().defValue()));
				}
				catch(IllegalAccessException e)
				{
					_log.error("Exception: " + e, e);
				}
			}
		}
	}

	public void write(String file)
	{
		Document document = DocumentFactory.getInstance().createDocument();
		Element rootElement = document.addElement("list");
		for(Map.Entry<Class<?>, PropertyRootImpl> entry : _mapping.entrySet())
			write(rootElement, entry.getValue());

		try
		{
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), OutputFormat.createPrettyPrint());
			writer.write(document);
			writer.close();
		}
		catch(Exception e)
		{
			_log.error(e);
		}
	}

	private void write(Element e, PropertyRootImpl propertyRoot)
	{
		Element rootElement = e.addElement(propertyRoot.getName());
		for(Map.Entry<Property, Field> entry : propertyRoot.getFieldMap().entrySet())
		{
			Object val = null;
			try
			{
				val = entry.getValue().get(null);
			}
			catch(IllegalAccessException e1)
			{
				e1.printStackTrace();
			}

			if(val != null)
			{
				Element proElement = rootElement.addElement(entry.getKey().value());

				proElement.setText(val.toString());
			}
		}
	}

	public void addMappingClass(Class<?> clazz)
	{
		PropertyRoot an = clazz.getAnnotation(PropertyRoot.class);
		if(an == null)
			throw new IllegalArgumentException();

		PropertyRootImpl propertyRoot;
		_mapping.put(clazz, propertyRoot = new PropertyRootImpl(an.value()));

		for(Field f : clazz.getDeclaredFields())
		{
			if(f.isAnnotationPresent(Property.class))
			{
				propertyRoot.addField(f.getAnnotation(Property.class), f);
			}
			else if(f.isAnnotationPresent(PropertyRoot.class))
			{

			}
		}
	}
}
