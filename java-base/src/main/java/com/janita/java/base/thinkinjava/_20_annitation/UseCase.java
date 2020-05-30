package com.janita.java.base.thinkinjava._20_annitation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UseCase
 *
 * @author zhucj
 * @since 20200528
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {

    int id();

    String description() default "no description";
}
