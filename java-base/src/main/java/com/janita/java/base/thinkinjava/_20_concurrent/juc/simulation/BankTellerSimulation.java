package com.janita.java.base.thinkinjava._20_concurrent.juc.simulation;//: concurrency/BankTellerSimulation.java
// Using queues and multithreading.
// {Args: 5}

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Read-only objects don't require synchronization:
class Customer {

    /**
     * 顾客被服务的时间
     */
    private final int serviceTime;

    public Customer(int tm) {
        serviceTime = tm;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

// Teach the customer line to display itself:
class CustomerLine extends ArrayBlockingQueue<Customer> {

    //有届队列
    public CustomerLine(int maxLineSize) {
        super(maxLineSize);
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}

// Randomly add customers to a queue:
class CustomerGenerator implements Runnable {

    private CustomerLine customers;

    private static Random rand = new Random(47);

    /**
     * 构造函数传入一个队列，保存生成的顾客对象
     *
     * @param cq
     */
    public CustomerGenerator(CustomerLine cq) {
        customers = cq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                //就是一段时间内来一些顾客，并且他们在排队
                customers.put(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}

/**
 * 银行出纳员
 */
class Teller implements Runnable, Comparable<Teller> {

    private static int counter = 0;

    private final int id = counter++;

    // Customers served during this shift:
    private int customersServed = 0;

    /**
     * 该出纳员前面的顾客队伍
     */
    private CustomerLine customers;

    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {
        customers = cq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //从队列的前面开始服务顾客
                Customer customer = customers.take();
                //睡眠顾客被服务的时间
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {//在该出纳员对象上同步
                    customersServed++;//服务完一个顾客数量+1
                    while (!servingCustomerLine) {//当队伍空的时候，就休息
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    /**
     * 去做其他时间，不为顾客提供服务了，并且把服务数量清零
     * 下班了
     */
    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }

    /**
     * 被要求上班了
     */
    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller " + id + " ";
    }

    public String shortString() {
        return "T" + id;
    }

    // Used by priority queue:
    @Override
    public synchronized int compareTo(Teller other) {
        //根据每个出纳员服务人数排序,使得工作量最小的出纳员被选出
        return Integer.compare(customersServed, other.customersServed);
    }
}

/**
 * 出纳员经理
 */
class TellerManager implements Runnable {

    private ExecutorService exec;

    private CustomerLine customers;

    private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();

    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();

    /**
     * 每隔一段时间调整一下出纳员的数量
     */
    private int adjustmentPeriod;

    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e, CustomerLine customers, int adjustmentPeriod) {
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // Start with a single teller:
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumber() {
        // This is actually a control system. By adjusting
        // the numbers, you can reveal stability issues in
        // the control mechanism.
        // If line is too long, add another teller:
        if (customers.size() / workingTellers.size() > 2) {
            // If tellers are on break or doing
            // another job, bring one back:
            if (tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            // Else create (hire) a new teller
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
        // If line is short enough, remove a teller:
        if (workingTellers.size() > 1 &&
                customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }
        // If there is no line, we only need one teller:
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    // Give a teller a different job or a break:
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() {
        return "TellerManager ";
    }
}

public class BankTellerSimulation {

    static final int MAX_LINE_SIZE = 50;

    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // If line is too long, customers will leave:
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);

        //该任务负责拉客户
        CustomerGenerator runner = new CustomerGenerator(customers);
        exec.execute(runner);

        // Manager will add and remove tellers as necessary:
        //经理负责管理出纳员：安排工作，招聘等
        TellerManager tellerManager = new TellerManager(exec, customers, ADJUSTMENT_PERIOD);
        exec.execute(tellerManager);

        if (args.length > 0) // Optional argument
        {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
} /* Output: (Sample)
[429][200][207] { T0 T1 }
[861][258][140][322] { T0 T1 }
[575][342][804][826][896][984] { T0 T1 T2 }
[984][810][141][12][689][992][976][368][395][354] { T0 T1 T2 T3 }
Teller 2 interrupted
Teller 2 terminating
Teller 1 interrupted
Teller 1 terminating
TellerManager interrupted
TellerManager terminating
Teller 3 interrupted
Teller 3 terminating
Teller 0 interrupted
Teller 0 terminating
CustomerGenerator interrupted
CustomerGenerator terminating
*///:~
