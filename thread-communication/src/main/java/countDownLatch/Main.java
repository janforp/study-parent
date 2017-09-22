package countDownLatch;

/**
 * Created by Janita on 2017/9/22 0022-上午 11:34
 * 该类是：
 */
public class Main {

    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}
