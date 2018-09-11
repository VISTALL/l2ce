package com.jdevelopstation.l2ce.version.node.data;

import org.dom4j.Element;
import com.jdevelopstation.l2ce.version.node.ClientNode;

/**
 * @author VISTALL
 * @date 9:25/18.05.2011
 */
public interface ClientDataNode extends ClientNode
{
	String getName();

	Object getValue();

	void setValue(Object val);

	void toXML(Element e);

	void setHidden(boolean hidden);

	boolean isHidden();
}
