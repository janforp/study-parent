package com.janita.java.base.thinkinjava._20_annitation.database;

public @interface Uniqueness {

    Constraints constraints() default @Constraints(unique = true);
}
