package design.masterWorker.self;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Janita on 2017/9/25 0025-下午 2:46
 * 该类是：
 */
public class WorkerSelf implements Runnable{


    private ConcurrentLinkedQueue<TaskSelf> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    void setWorkQueue(ConcurrentLinkedQueue<TaskSelf> workQueue) {
        this.workQueue = workQueue;
    }

    void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            TaskSelf task = this.workQueue.poll();
            if (task == null) break;
            //真正的执行任务
            Object result = handle(task);
            this.resultMap.put(task.getId() + "", result);
        }
    }

    private Object handle(TaskSelf task) {
        Object result = null;
        try {
            //模拟处理任务的耗时
            Thread.sleep(500);
            result = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
