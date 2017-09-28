package bhz.generate2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bhz.generate1.Trade;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {  
    public static void main(String[] args) throws InterruptedException {  
    	long beginTime=System.currentTimeMillis();
        int bufferSize=1024;
        ExecutorService executor=Executors.newFixedThreadPool(8);
        Disruptor<Trade> disruptor = new Disruptor<>(Trade::new, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());
//        linXing(disruptor, new Handler1(),new Handler2(), new Handler3());
//        shunXu(disruptor, new Handler1(),new Handler2(), new Handler3());
        liuBianXing(disruptor,new Handler1(),new Handler2(),new Handler3(),new Handler4(),new Handler5());


        disruptor.start();//启动
        CountDownLatch latch=new CountDownLatch(1);  
        //生产者准备  
        executor.submit(new TradePublisher(latch, disruptor));
        latch.await();//等待生产者完事.
        disruptor.shutdown();
        executor.shutdown();  
        System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime));  
    }

    /**
     * 菱形操作
     * 数据线被 handler1 及 handler2 并行处理，处理完之后，再被 handler3 处理
     */
    private static void linXing(Disruptor<Trade> disruptor, EventHandler<Trade> handler1, EventHandler<Trade> handler2, EventHandler<Trade> handler3) {
        //使用disruptor创建消费者组C1,C2
        EventHandlerGroup<Trade> handlerGroup = disruptor.handleEventsWith(handler1, handler2);
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
        handlerGroup.then(handler3);
    }

    /**
     * 顺序操作
     * 数据按顺序执行，第一个处理器全部处理完之后再交给后面的处理器
     */
    private static void shunXu(Disruptor<Trade> disruptor, EventHandler<Trade> handler1, EventHandler<Trade> handler2, EventHandler<Trade> handler3) {
        disruptor.handleEventsWith(handler1).handleEventsWith(handler2).handleEventsWith(handler3);
    }

    //六边形操作
    private static void liuBianXing(Disruptor<Trade> disruptor, EventHandler<Trade> handler1, EventHandler<Trade> handler2, EventHandler<Trade> handler3, EventHandler<Trade> handler4, EventHandler<Trade> handler5) {
        disruptor.handleEventsWith(handler1, handler2);
        disruptor.after(handler1).handleEventsWith(handler4);
        disruptor.after(handler2).handleEventsWith(handler5);
        disruptor.after(handler4, handler5).handleEventsWith(handler3);
    }
}