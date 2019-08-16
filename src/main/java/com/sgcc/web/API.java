package com.sgcc.web;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 手机API接口注释，被此注解作用的接口将会获取到手机的一些基本信息
 * @author juyuw
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface API {
    public String name() default "";
}
