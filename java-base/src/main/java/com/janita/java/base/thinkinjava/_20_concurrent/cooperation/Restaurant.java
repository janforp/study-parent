package com.janita.java.base.thinkinjava._20_concurrent.cooperation;//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.janita.java.base.thinkinjava.util.Print.print;
import static com.janita.java.base.thinkinjava.util.Print.printnb;

//生产者与消费者
class Meal {

    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

//服务员：消费者
class WaitPerson implements Runnable {

    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //服务员对象锁
                synchronized (this) {
                    //菜还没有生产好
                    while (restaurant.meal == null) {//使用while的原因：在并发的时候，某个其他的任务可能会在该服务员任务被唤醒的时候，会突然插足并拿走订单(meal)，唯一安全的方式是使用while自校验
                        //可能存在被唤醒之后再次while，发现订单被其他服务员拿走了（当然在此例子中不会出现）
                        wait(); // ... for the chef to produce a meal
                    }
                }
                //wait()被唤醒，有菜了
                print("Waitperson got " + restaurant.meal);
                //拿到厨师对象锁，并且开始消费，避免在消费的时候厨师又开始生产
                synchronized (restaurant.chef) {
                    restaurant.meal = null;//消费菜
                    // Ready for another，通知生产者
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

//生产者
class Chef implements Runnable {

    private Restaurant restaurant;

    //菜的号码，一个厨师最多做10道菜
    private int count = 0;

    public Chef(Restaurant r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //厨师对象锁
                synchronized (this) {
                    //上一道菜还没被消费，等待消费者消费
                    while (restaurant.meal != null) {
                        wait(); // ... for the meal to be taken
                    }
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                //获取服务员对象锁，并且开始生产，避免在生产的时候服务员续费
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    //通知消费者
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

public class Restaurant {

    Meal meal;

    ExecutorService exec = Executors.newCachedThreadPool();

    //餐馆唯一的服务员：消费者
    WaitPerson waitPerson = new WaitPerson(this);

    //餐馆唯一的厨师：生产者
    Chef chef = new Chef(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {

        //唯一的餐馆
        new Restaurant();
    }
} /* Output:
Order up! Waitperson got Meal 1
Order up! Waitperson got Meal 2
Order up! Waitperson got Meal 3
Order up! Waitperson got Meal 4
Order up! Waitperson got Meal 5
Order up! Waitperson got Meal 6
Order up! Waitperson got Meal 7
Order up! Waitperson got Meal 8
Order up! Waitperson got Meal 9
Out of food, closing
WaitPerson interrupted
Order up! Chef interrupted
*///:~
