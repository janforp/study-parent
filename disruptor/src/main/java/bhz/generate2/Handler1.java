package bhz.generate2;


import bhz.generate1.Trade;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Handler1 implements EventHandler<Trade>,WorkHandler<Trade> {
    AtomicInteger integer = new AtomicInteger(1);

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
    	System.out.println("handler1: set name --- " + integer.getAndIncrement());
    	event.setName("h1");
//    	Thread.sleep(1000);
    }  
}