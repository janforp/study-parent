package bhz.generate2;

import bhz.generate1.Trade;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Handler2 implements EventHandler<Trade> {
    private AtomicInteger integer = new AtomicInteger(1);
    @Override
    public void onEvent(Trade event, long sequence,  boolean endOfBatch) throws Exception {  
    	System.out.println("handler2: set price -- " + integer.getAndIncrement());
    	event.setPrice(17.0);
//    	Thread.sleep(1000);
    }  
}