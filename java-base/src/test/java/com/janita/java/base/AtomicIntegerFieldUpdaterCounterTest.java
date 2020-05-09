package com.janita.java.base;

import com.janita.java.base.concurrent.atomic.AtomicIntegerFieldUpdaterCounter;
import com.janita.java.base.entity.Details;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 类说明：AtomicIntegerFieldUpdaterCounterTest
 *
 * @author zhucj
 * @since 20200423
 */
public class AtomicIntegerFieldUpdaterCounterTest {

    // Initialises reader threads and starts them
    private ReaderThread[] startReaderThreads(int numberThreads, int maxNumberCalls, CountDownLatch countDownLatch) {

        ReaderThread[] threads = new ReaderThread[numberThreads];

        for (int i = 0; i < numberThreads; i++) {

            threads[i] = new ReaderThread(countDownLatch, maxNumberCalls);
        }

        for (int i = 0; i < numberThreads; i++) {

            threads[i].start();
        }

        return threads;
    }

    // Starts writer threads and starts them
    private WriterThread[] startWriterThreads(int numberThreads, int maxNumberCalls,
            ReaderThread[] readerThreads, AtomicIntegerFieldUpdaterCounter atomicIntegerFieldUpdaterCounter) {

        WriterThread[] threads = new WriterThread[numberThreads];

        for (int i = 0; i < numberThreads; i++) {

            threads[i] = new WriterThread(maxNumberCalls, readerThreads, atomicIntegerFieldUpdaterCounter);
        }

        for (int i = 0; i < numberThreads; i++) {

            threads[i].start();
        }

        return threads;
    }

    /**
     * This test is a bit complicated as it checks that when
     * atomicIntegerFieldUpdater.addAndGet( details, 1 ) was called on the
     * Details object held in the reader threads by multiple writer threads
     * that no values were skipped, that there were no duplicates and that
     * the end counter values were correct.
     * <p>
     * Basically the test checks whether
     * atomicIntegerFieldUpdater.addAndGet( details, 1 ) works properly
     *
     * @throws InterruptedException ex
     */
    @Test
    public void testAddOne() throws InterruptedException {

        int numberThreads = 20;
        int maxNumberCallsPerThread = 1000;

        CountDownLatch countDownLatch = new CountDownLatch(numberThreads);

        ReaderThread[] readerThreads = startReaderThreads(numberThreads,
                maxNumberCallsPerThread * numberThreads, countDownLatch);

        AtomicIntegerFieldUpdaterCounter atomicIntegerFieldUpdaterCounter = new AtomicIntegerFieldUpdaterCounter();

        WriterThread[] writerThreads = startWriterThreads(numberThreads, maxNumberCallsPerThread,
                readerThreads, atomicIntegerFieldUpdaterCounter);

        // wait for reader threads to finish reading
        countDownLatch.await();

        for (int i = 0; i < readerThreads.length; i++) {

            System.out.println(readerThreads[i].getName() + " " +
                    readerThreads[i].getDetails().getNumberTimesInvoked());

            // Check that each of the reader threads had their volatile
            // "numberTimesInvoked" incremented to 20000 by the write threads
            org.junit.Assert.assertEquals(
                    numberThreads * maxNumberCallsPerThread,
                    readerThreads[i].getDetails().getNumberTimesInvoked());
        }

        Map<Integer, TreeSet<Integer>> countersByReaderMap = new HashMap<>();

        for (int i = 0; i < readerThreads.length; i++) {

            countersByReaderMap.put(new Integer(i), new TreeSet<>());
        }

        List<Integer> readerCountList;
        TreeSet<Integer> countSet;

        // Check that that when
        // atomicIntegerFieldUpdater.addAndGet( details, 1 );
        // was called that each worker thread got a unique value. i.e. there
        // were no duplicates.
        //
        // We use a TreeSet for each Reader which basically merges the values
        // each writer updated a particular reader with into one set. During
        // the merge we check for duplicates.
        //
        for (int i = 0; i < writerThreads.length; i++) {

            for (int j = 0; j < readerThreads.length; j++) {

                countSet = countersByReaderMap.get(new Integer(j));

                readerCountList = writerThreads[i].getReaderCountByReaderMap().get(new Integer(j));

                for (Integer count : readerCountList) {

                    if (countSet.contains(count)) {

                        org.junit.Assert.fail("duplicate");
                    } else {

                        countSet.add(count);
                    }
                }
            }
        }

        // Check that there were no values skipped when the counter
        // was incremented

        int numberCounterIncrements = numberThreads * maxNumberCallsPerThread;
        Iterator<Integer> counterReadingsIterator;

        for (int i = 0; i < readerThreads.length; i++) {

            Set<Integer> counterReadings =
                    countersByReaderMap.get(new Integer(i));

            counterReadingsIterator = counterReadings.iterator();

            for (int j = 0; j < numberCounterIncrements; j++) {

                org.junit.Assert.assertEquals(
                        new Integer(j + 1), counterReadingsIterator.next());
            }
        }
    }

    public static void main(String args[]) {

        JUnitCore junitCore = new JUnitCore();
        junitCore.addListener(new TextListener(System.out));
        junitCore.run(AtomicIntegerFieldUpdaterCounterTest.class);
    }
}

class ReaderThread extends Thread {

    private final CountDownLatch countDownLatch;

    private final Details details;

    private final int maxNumberCalls;

    public ReaderThread(CountDownLatch countDownLatch, int maxNumberCalls) {

        details = new Details();
        this.countDownLatch = countDownLatch;
        this.maxNumberCalls = maxNumberCalls;
    }

    public Details getDetails() {

        return this.details;
    }

    @Override
    public void run() {

        while (true) {
            if (details.getNumberTimesInvoked() >= maxNumberCalls) {
                break;
            }
            LockSupport.parkNanos(1);
        }
        countDownLatch.countDown();
    }
}

class WriterThread extends Thread {

    private final int maxNumberCalls;

    private final ReaderThread[] readerThreads;

    private final AtomicIntegerFieldUpdaterCounter atomicIntegerFieldUpdaterCounter;

    private final Map<Integer, List<Integer>> readerCountByReaderMap = new HashMap<>();

    public WriterThread(int maxNumberCalls, ReaderThread[] readerThreads,
            AtomicIntegerFieldUpdaterCounter atomicIntegerFieldUpdaterCounter) {

        this.maxNumberCalls = maxNumberCalls;
        this.readerThreads = readerThreads;

        this.atomicIntegerFieldUpdaterCounter = atomicIntegerFieldUpdaterCounter;

        for (int i = 0; i < readerThreads.length; i++) {

            readerCountByReaderMap.put(new Integer(i), new ArrayList<>());
        }
    }

    public Map<Integer, List<Integer>> getReaderCountByReaderMap() {

        return readerCountByReaderMap;
    }

    @Override
    public void run() {

        int count;
        List<Integer> counts;

        for (int i = 0; i < maxNumberCalls; i++) {

            for (int j = 0; j < readerThreads.length; j++) {

                counts = readerCountByReaderMap.get(new Integer(j));

                count = atomicIntegerFieldUpdaterCounter.addOne(readerThreads[j].getDetails());

                counts.add(count);
            }
        }
    }
}
