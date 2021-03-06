package com.jdevelopstation.commons.data.xml;

/**
 * @author VISTALL
 * @date 9:04/19.05.2011
 */
public abstract class AbstractHolder extends LoggerObject
{
	public void log()
	{
		info(String.format("loaded %d %s(s) count.", size(), formatOut(getClass().getSimpleName().replace("Holder", "")).toLowerCase()));
	}

	protected void process()
	{

	}

	public abstract int size();

	public abstract void clear();

	private static String formatOut(String st)
	{
		char[] chars = st.toCharArray();
		StringBuilder buf = new StringBuilder(chars.length);

		for (char ch : chars)
		{
			if (Character.isUpperCase(ch))
				buf.append(" ");

			buf.append(Character.toLowerCase(ch));
		}

		return buf.toString();
	}
}