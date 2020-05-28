package com.janita.java.base.thinkinjava._19_enum;

import com.janita.java.base.thinkinjava.util.Enums;

/**
 * Food
 *
 * @author zhucj
 * @since 20200528
 */
public interface Food {

    enum Appetizer implements Food {
        SALAD,
        SOUP,
        SPRING_ROLLS;
    }

    enum MainCourse implements Food {
        LASAGNE,
        BURRITO,
        PAD_THAI,
        LENTILS,
        HUMMOUS,
        VINDALOO;
    }

    enum Dessert implements Food {
        TIRAMISU,
        GELATO,
        BLACK_FOREST_CAKE,
        FRUIT,
        CREME_CARAMEL;
    }

    enum Coffee implements Food {
        BLACK_COFFEE,
        DECAF_COFFEE,
        ESPRESSO,
        LATTE,
        CAPPUCCINO,
        TEA,
        HERB_TEA;
    }
}

class TypeOfFood {

    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;
    }
}

enum Course {
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

class Meal {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
} /* Output:
SPRING_ROLLS
VINDALOO
FRUIT
DECAF_COFFEE
---
SOUP
VINDALOO
FRUIT
TEA
---
SALAD
BURRITO
FRUIT
TEA
---
SALAD
BURRITO
CREME_CARAMEL
LATTE
---
SOUP
BURRITO
TIRAMISU
ESPRESSO
---
*///:~

enum SecurityCategory {

    STOCK(Security.Stock.class),

    BOND(Security.Bond.class);

    Security[] values;

    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }

    interface Security {

        enum Stock implements Security {
            SHORT,
            LONG,
            MARGIN
        }

        enum Bond implements Security {
            MUNICIPAL,
            JUNK
        }
    }

    public Security randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            SecurityCategory category = Enums.random(SecurityCategory.class);
            System.out.println(category + ": " + category.randomSelection());
        }
    }
} /* Output:
BOND: MUNICIPAL
BOND: MUNICIPAL
STOCK: MARGIN
STOCK: MARGIN
BOND: JUNK
STOCK: SHORT
STOCK: LONG
STOCK: LONG
BOND: MUNICIPAL
BOND: JUNK
*///:~

enum Meal2 {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    private Meal2(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public interface Food {

        enum Appetizer implements Food {
            SALAD,
            SOUP,
            SPRING_ROLLS;
        }

        enum MainCourse implements Food {
            LASAGNE,
            BURRITO,
            PAD_THAI,
            LENTILS,
            HUMMOUS,
            VINDALOO;
        }

        enum Dessert implements Food {
            TIRAMISU,
            GELATO,
            BLACK_FOREST_CAKE,
            FRUIT,
            CREME_CARAMEL;
        }

        enum Coffee implements Food {
            BLACK_COFFEE,
            DECAF_COFFEE,
            ESPRESSO,
            LATTE,
            CAPPUCCINO,
            TEA,
            HERB_TEA;
        }
    }

    public Food randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Meal2 meal : Meal2.values()) {
                Food food = meal.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
} /* Same output as Meal.java *///:~

