package com.janita.java.base.thinkinjava._16_array;

import java.util.Arrays;
import java.util.Random;

/**
 * 类说明：MultidimensionalPrimitiveArray
 *
 * @author zhucj
 * @since 20200528
 */
public class MultidimensionalPrimitiveArray {

    public static void main(String[] args) {
        int[][] a = {
                { 1, 2, 3, },
                { 4, 5, 6, },
        };
        System.out.println(Arrays.deepToString(a));
    }
}

class ThreeDWithNew {

    public static void main(String[] args) {
        // 3-D array with fixed length:
        int[][][] a = new int[2][2][4];
        System.out.println(Arrays.deepToString(a));

        int[][][][] b = new int[2][3][3][4];
        System.out.println(Arrays.deepToString(b));
    }
}

class RaggedArray {

    public static void main(String[] args) {
        Random rand = new Random(47);
        // 3-D array with varied-length vectors:
        int[][][] a = new int[rand.nextInt(7)][][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = new int[rand.nextInt(5)];
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
}

class MultidimensionalObjectArrays {

    public static void main(String[] args) {
        BerylliumSphere[][] spheres = {
                { new BerylliumSphere(), new BerylliumSphere() },
                { new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere() },
                { new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere() },
        };
        System.out.println(Arrays.deepToString(spheres));
    }
}

class AutoboxingArrays {

    public static void main(String[] args) {
        Integer[][] a = { // Autoboxing:
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
                { 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 },
                { 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 },
        };
        System.out.println(Arrays.deepToString(a));
    }
}

class AssemblingMultidimensionalArrays {

    public static void main(String[] args) {
        Integer[][] a;
        a = new Integer[3][];
        int length = a.length;
        for (int i = 0; i < length; i++) {
            a[i] = new Integer[3];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = i * j; // Autoboxing
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
}

class MultiDimWrapperArray {

    public static void main(String[] args) {
        Integer[][] a1 = { // Autoboxing
                { 1, 2, 3, },
                { 4, 5, 6, },
        };
        Double[][][] a2 = { // Autoboxing
                { { 1.1, 2.2 }, { 3.3, 4.4 } },
                { { 5.5, 6.6 }, { 7.7, 8.8 } },
                { { 9.9, 1.2 }, { 2.3, 3.4 } },
        };
        String[][] a3 = {
                { "The", "Quick", "Sly", "Fox" },
                { "Jumped", "Over" },
                { "The", "Lazy", "Brown", "Dog", "and", "friend" },
        };
        System.out.println("a1: " + Arrays.deepToString(a1));
        System.out.println("a2: " + Arrays.deepToString(a2));
        System.out.println("a3: " + Arrays.deepToString(a3));
    }
}