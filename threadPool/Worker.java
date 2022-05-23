package threadPool;

import UI.Notifier;
import UI.View;
import UI.NotifierType;
import UI.UpdateValue;

import java.util.List;

public class Worker extends Thread{
    private final List<Task> queue;
    private int period;
    private final int threadID;
    private View view;
    private WorkerPool pool;
    boolean isRunning = true;

    public Worker(List<Task> queue, int ID, View ui, WorkerPool pool){
        this.queue = queue;
        threadID = ID;
        view = ui;
        this.pool = pool;
    }

    @Override
    public void run(){
        Task task;
        while(isRunning){
            synchronized (queue){
                if(queue.isEmpty()){
                    view.updateUI(new Notifier(NotifierType.worker, threadID, new UpdateValue("Waiting for request")));
                    try {
                        queue.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    continue;
                }else{
                    task = queue.remove(0);
                    view.updateUI(new Notifier(NotifierType.threadPool, 0, new UpdateValue(queue.size())));
                }
            }
            view.updateUI(new Notifier(NotifierType.worker, threadID, new UpdateValue("Waiting for components")));
            task.doTask();
            view.updateUI(new Notifier(NotifierType.worker, threadID, new UpdateValue("Assembling")));
            try {
                sleep(period);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            pool.performedTask();
            view.updateUI(new Notifier(NotifierType.worker, threadID, new UpdateValue("Assembled")));
        }
    }

    public void setStopped(){
        isRunning = false;
    }

    public void setPeriod(int period){
        this.period = period;
    }

    public int getPeriod(){
        return period;
    }
}
