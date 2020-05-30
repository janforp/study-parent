package com.janita.java.base.thinkinjava._20_concurrent.atomic;//: concurrency/AtomicEvenGenerator.java
// Atomic classes are occasionally useful in regular code.
// {RunByHand}

import com.janita.java.base.thinkinjava._20_concurrent.EvenChecker;
import com.janita.java.base.thinkinjava._20_concurrent.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
