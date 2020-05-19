package com.janita.java.base.thinkinjava._14_type_param;

import java.util.ArrayList;
import java.util.List;

/**
 * RTTI（Run-Time Type Identification):运行时类型识别
 *
 * 类说明：FilledList
 *
 * @author zhucj
 * @since 20200528
 */
public class FilledList<T> {

    private Class<T> type;

    FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int n) throws IllegalAccessException, InstantiationException {
        List<T> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            T t = type.newInstance();
            list.add(t);
        }
        return list;
    }
}

class CountedInteger {

    private static long counter;

    private final long id = counter++;

    @Override
    public String toString() {
        return Long.toString(id);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
        List<CountedInteger> list = filledList.create(15);
        System.out.println(list);
    }
}

class Toy {

    @Override
    public String toString() {
        return "Toy";
    }
}

class FancyToy extends Toy {

    @Override
    public String toString() {
        return "FancyToy";
    }
}

class FancyToyTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<FancyToy> fancyToyClass = FancyToy.class;
        FancyToy fancyToy = fancyToyClass.newInstance();
        System.out.println(fancyToy);

        Class<? super FancyToy> superclass = fancyToyClass.getSuperclass();

        //        Class<Toy> toyClass = fancyToyClass.getSuperclass();

        Object object = superclass.newInstance();
        System.out.println(object.getClass());
    }
}

class Build {

}

class House extends Build {

}

class ClassCast {

    public static void main(String[] args) {
        Build build = new House();
        Class<House> houseClass = House.class;
        House house = houseClass.cast(build);
        System.out.println(house);
        System.out.println(houseClass.isInstance(house));
        System.out.println(Build.class.isInstance(house));
        System.out.println(Object.class.isInstance(house));
    }
}