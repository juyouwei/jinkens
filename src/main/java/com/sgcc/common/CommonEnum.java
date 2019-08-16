package com.sgcc.common;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

public interface CommonEnum {

	String DEFAULT_DESC_NAME = "desc";
	String DEFAULT_CODE_NAME = "code";

	default String getDesc() {
		Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_DESC_NAME);
		if (field == null)
			return null;
		try {
			field.setAccessible(true);
			return field.get(this).toString();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}


	default String getCode() {
		Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_CODE_NAME);
		if (field == null)
			return null;
		try {
			field.setAccessible(true);
			return field.get(this).toString();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 根据枚举类型和码返回具体枚举
	 * 
	 * @param code
	 * @param enumClass
	 *            枚举类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T valueOf(String code, Class<T> enumClass) {
		if (code == null)
			throw new IllegalArgumentException("CommonEnum value should not be null");
		if (enumClass.isAssignableFrom(CommonEnum.class))
			throw new IllegalArgumentException("illegal CommonEnum type");
		T[] enums = enumClass.getEnumConstants();
		for (T t : enums) {
			CommonEnum displayedEnum = (CommonEnum) t;
			if (code.equals(displayedEnum.getCode())) {
				return (T) displayedEnum;
			}
		}
		  throw new IllegalArgumentException("cannot parse integer: " + code + " to " + enumClass.getName());
	}
}
