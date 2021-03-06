package bhz.generate1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;

public class Main2 {  
    public static void main(String[] args) throws InterruptedException {  

        EventFactory<Trade> eventFactory = Trade::new;
        
        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(eventFactory, 1024);
        //当生产者快的时候使用
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
          
        ExecutorService executor = Executors.newFixedThreadPool(4);

        //消费者
        WorkHandler<Trade> handler = new TradeHandler();
        //消息处理器
        WorkerPool<Trade> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), handler);
          
        workerPool.start(executor);
          
        //下面这个生产8个数据
        for(int i=0;i<8;i++){  
            long seq=ringBuffer.next();  
            ringBuffer.get(seq).setPrice(Math.random()*9999);  
            ringBuffer.publish(seq);  
        }  
          
        Thread.sleep(1000);  
        workerPool.halt();  
        executor.shutdown();  
    }  
}