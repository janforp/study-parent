package communication.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Janita on 2017/9/22 0022-上午 11:31
 * 该类是：
 */
public class NetworkHealthChecker extends BaseHealthChecker{

    public NetworkHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
