package com.sgcc.web;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 将17位时间字符串转成指定格式的字符串(14位数字) 如 20190707164022187->2019-07-07 16:40:22
 * @author juyuw
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeFormat {
   public String value () default "";
}
