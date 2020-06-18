package com.janita.java.base.thinkinjava._19_enum;

import com.janita.java.base.thinkinjava.util.Enums;

/**
 * Course
 *
 * @author zhucj
 * @since 20200623
 */
public enum Course {

    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }
}
