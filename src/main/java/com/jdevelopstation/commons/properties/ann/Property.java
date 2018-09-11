package com.jdevelopstation.commons.properties.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.apache.commons.lang3.StringUtils;

/**
 * @author VISTALL
 * @date 23:54/05.06.2011
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Property
{
	String value();
	String defValue() default StringUtils.EMPTY;
}
