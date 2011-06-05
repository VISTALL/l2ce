package com.jdevelopstation.commons.properties.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author VISTALL
 * @date 23:54/05.06.2011
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyRoot
{
	String value();
}
