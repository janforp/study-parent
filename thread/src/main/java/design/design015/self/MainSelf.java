package design.design015.self;

import java.util.Random;

/**
 * Created by Janita on 2017/9/25 0025-下午 3:08
 * 该类是：
 */
public class MainSelf {

    public static void main(String[] args) {
        MasterSelf master = new MasterSelf(new WorkerSelf(), 30);

        Random r = new Random();
        for (int i = 1; i <= 100; i++) {
            TaskSelf task = new TaskSelf();
            task.setId(i);
            task.setName("任务" + i);
            task.setPrice(r.nextInt(1000));

            master.submit(task);
        }
        master.execute();

        long now = System.currentTimeMillis();
        while (true) {
            if (master.isComplete()) {
                int result = master.getResult();
                System.out.println("\n***** 最后结果是 : " + result +", 耗时 ： " + (System.currentTimeMillis() - now));
                break;
            }
        }
    }
}
