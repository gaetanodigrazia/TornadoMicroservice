/**
 * 
 */
package com.tornado.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;

@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.PARAMETER, ElementType.FIELD})
/**
 * @author gaeta
 *
 */
@Bean
public @interface TestConstructorAnnotation {
	String name() default "";
}
