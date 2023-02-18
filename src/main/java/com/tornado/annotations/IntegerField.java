/**
 * 
 */
package com.tornado.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.SOURCE)
@Target(FIELD)
@Inherited
@Qualifier
/**
 * @author gaeta
 *
 */
public @interface IntegerField {

}
