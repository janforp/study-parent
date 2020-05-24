package com.janita.java.base.thinkinjava._17_container;

import com.janita.java.base.thinkinjava.typeinfo.pets.Person;
import com.janita.java.base.thinkinjava.typeinfo.pets.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 类说明：Individual
 *
 * @author zhucj
 * @since 20200528
 */
public class Individual implements Comparable<Individual> {

    private static long counter = 0;

    private final long id = counter++;

    private String name;

    public Individual(String name) {
        this.name = name;
    }

    // 'name' is optional:
    public Individual() {
    }

    public String toString() {
        return getClass().getSimpleName() + (name == null ? "" : " " + name);
    }

    public long id() {
        return id;
    }

    public boolean equals(Object o) {
        return o instanceof Individual
                && id == ((Individual) o).id;
    }

    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 37 * result + name.hashCode();
        }
        result = 37 * result + (int) id;
        return result;
    }

    public int compareTo(Individual arg) {
        // Compare by class name first:
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();

        int firstCompare = first.compareTo(argFirst);

        if (firstCompare != 0) {
            return firstCompare;
        }

        if (name != null && arg.name != null) {
            int secondCompare = name.compareTo(arg.name);
            if (secondCompare != 0) {
                return secondCompare;
            }
        }
        return (Long.compare(arg.id, id));
    }
}

class IndividualTest {

    public static void main(String[] args) {
        Set<Individual> pets = new TreeSet<Individual>();

        Map<Person, List<? extends Pet>> personListMap = MapOfList.petPeople;
        Collection<List<? extends Pet>> listCollection = personListMap.values();
        for (List<? extends Pet> petList : listCollection) {
            for (Pet p : petList) {
                pets.add(p);
            }
        }
        System.out.println(pets);
    }
} /* Output:
[Cat Elsie May, Cat Pinkola, Cat Shackleton, Cat Stanford aka Stinky el Negro, Cymric Molly, Dog Margrett, Mutt Spot, Pug Louie aka Louis Snorkelstein Dupree, Rat Fizzy, Rat Freckly, Rat Fuzzy]
*///:~