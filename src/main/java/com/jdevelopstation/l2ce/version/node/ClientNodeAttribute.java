package com.jdevelopstation.l2ce.version.node;

/**
 * @author VISTALL
 * @since 2018-09-10
 */
public class ClientNodeAttribute
{
	private String myName;
	private String myExpression;

	public ClientNodeAttribute(String name, String expression)
	{
		myName = name;
		myExpression = expression;
	}

	public String getName()
	{
		return myName;
	}

	public String getExpression()
	{
		return myExpression;
	}
}
