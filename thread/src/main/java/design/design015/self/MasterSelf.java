package design.design015.self;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Janita on 2017/9/25 0025-下午 2:45
 * 该类是：
 */
public class MasterSelf {

    //1.装任务集合
    private ConcurrentLinkedQueue<TaskSelf> workQueue = new ConcurrentLinkedQueue<>();
    //2.装所有worker对象
    private HashMap<String, Thread> workers = new HashMap<>();
    //3.装所有结果
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    //4. Master构造方法
    public MasterSelf(WorkerSelf worker, int workCount) {
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        for (int i = 0; i < workCount; i++) {
            //key:表示每一个worker的名称，value：表示线程执行对象
            workers.put("子节点"+Integer.toString(i), new Thread(worker));
        }
    }

    void submit(TaskSelf task) {
        this.workQueue.add(task);
    }

    void execute() {
        for (Map.Entry<String, Thread> me :workers.entrySet()) {
            me.getValue().start();
        }
    }

    boolean isComplete() {
        for (Map.Entry<String, Thread> me :workers.entrySet()) {
            //状态是否是停止状态
            if (me.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public int getResult() {
        int result = 0;
        for (Map.Entry<String, Object> me :resultMap.entrySet()) {
            result+= (Integer) me.getValue();
        }
        return result;
    }
}
