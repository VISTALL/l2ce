package com.jdevelopstation.l2ce.version.node.file.reader;

import java.nio.ByteBuffer;

/**
 * @author VISTALL
 * @date 8:08/18.05.2011
 */
public interface ReadWriteType<T>
{
	T read(ByteBuffer buffer);

	void write(Object val, ByteBuffer buff);
}
