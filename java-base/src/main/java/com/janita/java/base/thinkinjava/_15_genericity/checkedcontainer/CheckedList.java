package com.janita.java.base.thinkinjava._15_genericity.checkedcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类说明：CheckedList
 *
 * @author zhucj
 * @since 20200528
 */
public class CheckedList {

    static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        ArrayList<Dog> dogs1 = new ArrayList<>();
        oldStyleMethod(dogs1);

        List<Dog> dogs2 = Collections.checkedList(new ArrayList<>(), Dog.class);

        try {
            oldStyleMethod(dogs2);
        } catch (Exception e) {
            System.out.println(e);
        }

        List<Pet> pets = Collections.checkedList(new ArrayList<>(), Pet.class);
        pets.add(new Dog());
        pets.add(new Cat());
    }
}

class Pet {

}

class Cat extends Pet {

}

class Dog extends Pet {

}
