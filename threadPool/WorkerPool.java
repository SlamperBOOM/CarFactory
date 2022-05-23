package threadPool;

import UI.Notifier;
import UI.NotifierType;
import UI.UpdateValue;
import UI.View;

import java.util.*;

public class WorkerPool {

    private Set<Worker> availableThread;
    private final List<Task> queue;
    private long performedTasks;

    private View view;

    public WorkerPool(int threadsCount,  View ui){

        view = ui;

        availableThread = new HashSet<>();
        queue = new ArrayList<>();

        for(int i=0; i< threadsCount; ++i){
            availableThread.add(new Worker(queue, i+1, view, this));
        }
    }

    public void start(){
        for (Worker worker : availableThread) {
            worker.start();
        }
    }

    public void execute(Task task){
        synchronized (queue){
            queue.add(task);
            queue.notify();
            view.updateUI(new Notifier(NotifierType.threadPool, 0, new UpdateValue(queue.size())));
        }
    }

    public void stop(){
        for(Worker worker: availableThread){
            worker.setStopped();
        }
    }

    public synchronized void performedTask(){
        performedTasks++;
        view.updateUI(new Notifier(NotifierType.threadPool, 1, new UpdateValue(performedTasks)));
    }

    public void setPeriod(int period){
        for (Worker worker : availableThread) {
            worker.setPeriod(period);
        }
    }
}
