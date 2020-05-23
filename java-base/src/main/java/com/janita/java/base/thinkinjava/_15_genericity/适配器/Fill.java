package com.janita.java.base.thinkinjava._15_genericity.适配器;

import com.janita.java.base.thinkinjava._15_genericity.SimpleQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 类说明：Fill
 *
 * @author zhucj
 * @since 20200528
 */
public class Fill {

    public static <T> void fill(Collection<T> collection, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++)
        // Assumes default constructor:
        {
            try {
                collection.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Contract {

    private static long counter = 0;

    private final long id = counter++;

    public String toString() {
        return getClass().getName() + " " + id;
    }
}

class TitleTransfer extends Contract {

}

class FillTest {

    public static void main(String[] args) {
        List<Contract> contracts = new ArrayList<Contract>();
        Fill.fill(contracts, Contract.class, 3);
        Fill.fill(contracts, TitleTransfer.class, 2);
        for (Contract c : contracts) {
            System.out.println(c);
        }
        SimpleQueue<Contract> contractQueue = new SimpleQueue<Contract>();
        // Won't work. fill() is not generic enough:
        // Fill.fill(contractQueue, Contract.class, 3);
    }
}