package com.janita.java.base.thinkinjava._17_container.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * 类说明：References
 *
 * @author zhucj
 * @since 20200528
 */
class VeryBig {

    private static final int SIZE = 10000;

    private long[] la = new long[SIZE];

    private String ident;

    public VeryBig(String id) {
        ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}

public class References {

    private static ReferenceQueue<VeryBig> referenceQueue = new ReferenceQueue<VeryBig>();

    public static void checkQueue() {
        Reference<? extends VeryBig> reference = referenceQueue.poll();
        if (reference != null) {

            VeryBig veryBig = reference.get();
            System.out.println("In queue: " + veryBig);
        }
    }

    public static void main(String[] args) {
        int size = 10;
        // Or, choose size via the command line:
        if (args.length > 0) {
            size = new Integer(args[0]);
        }
        LinkedList<SoftReference<VeryBig>> softReferenceLinkedList = new LinkedList<SoftReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            VeryBig veryBig = new VeryBig("Soft " + i);
            SoftReference<VeryBig> veryBigSoftReference = new SoftReference<>(veryBig, referenceQueue);

            softReferenceLinkedList.add(veryBigSoftReference);

            SoftReference<VeryBig> last = softReferenceLinkedList.getLast();
            System.out.println("Just created: " + last);
            checkQueue();
        }

        System.out.println("SoftReference 结束");

        LinkedList<WeakReference<VeryBig>> weakReferenceLinkedList = new LinkedList<WeakReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            VeryBig veryBig = new VeryBig("Weak " + i);
            WeakReference<VeryBig> veryBigWeakReference = new WeakReference<>(veryBig, referenceQueue);

            weakReferenceLinkedList.add(veryBigWeakReference);

            WeakReference<VeryBig> last = weakReferenceLinkedList.getLast();
            System.out.println("Just created: " + last);
            checkQueue();
        }

        System.out.println("WeakReference 结束");

        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));
        System.gc();


        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i), referenceQueue));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}
