package com.janita.java.base.thinkinjava._17_container;

import com.janita.java.base.thinkinjava._15_genericity.Generator;
import com.janita.java.base.thinkinjava._16_array.CountingGenerator;
import com.janita.java.base.thinkinjava._16_array.RandomGenerator;
import com.janita.java.base.thinkinjava.util.Pair;

import java.util.Iterator;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：MapDataTest
 *
 * @author zhucj
 * @since 20200528
 */
class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {

    private int size = 9;

    private int number = 1;

    private char letter = 'A';

    public Pair<Integer, String> next() {
        return new Pair<Integer, String>(number++, "" + letter++);
    }

    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            public Integer next() {
                return number++;
            }

            public boolean hasNext() {
                return number < size;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class MapDataTest {

    public static void main(String[] args) {
        // Pair Generator:
        Letters pairGen = new Letters();
        MapData<Integer, String> integerStringMapData = MapData.map(pairGen, 11);
        print(integerStringMapData);

        // Two separate generators:
        CountingGenerator.Character characterKeyGen = new CountingGenerator.Character();
        RandomGenerator.String stringValueGen = new RandomGenerator.String(3);
        MapData<Character, String> characterStringMapData = MapData.map(characterKeyGen, stringValueGen, 8);
        print(characterStringMapData);

        // A key Generator and a single value:
        CountingGenerator.Character characterKeyGen2 = new CountingGenerator.Character();
        MapData<Character, String> stringMapData = MapData.map(characterKeyGen2, "Value", 6);
        print(stringMapData);


        // An Iterable and a value Generator:
        Letters lettersIterable = new Letters();
        RandomGenerator.String stringValueGen2 = new RandomGenerator.String(3);
        MapData<Integer, String> mapData = MapData.map(lettersIterable, stringValueGen2);
        print(mapData);

        // An Iterable and a single value:
        Letters lettersIterable2 = new Letters();
        print(MapData.map(lettersIterable2, "Pop"));
    }
}
