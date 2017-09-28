package bhz.generate2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import bhz.generate1.Trade;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

public class TradePublisher implements Runnable {  
	
    private Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.disruptor=disruptor;  
        this.latch=latch;  
    }  
  
    @Override  
    public void run() {  
    	TradeEventTranslator eventTranslator = new TradeEventTranslator();
        for(int i = 0; i< 1; i++){
            disruptor.publishEvent(eventTranslator);
        }  
        latch.countDown();  
    }  
      
}  
  
class TradeEventTranslator implements EventTranslator<Trade>{  
    
	private Random random=new Random();  
    
	@Override  
    public void translateTo(Trade event, long sequence) {  
        this.generateTrade(event);  
    }  
    
	private void generateTrade(Trade trade){
        trade.setPrice(random.nextDouble()*9999);
    }
}