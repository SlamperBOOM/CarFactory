package threadPool.workerPool;

import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.details.Car;
import factory.storages.carStorage.CarStorage;

import java.util.List;

public class Worker extends Thread{
    private final CarStorage storage;
    private final List<AssembleTask> carQueue;
    private int period;
    private final int workerID;
    private View view;
    private WorkerPool pool;
    boolean isRunning = true;

    public Worker(CarStorage storage, List<AssembleTask> carQueue, int ID, View ui, WorkerPool pool){
        this.storage = storage;
        this.carQueue = carQueue;
        workerID = ID;
        view = ui;
        this.pool = pool;
    }

    @Override
    public void run(){
        AssembleTask task;
        while(isRunning){
            synchronized (carQueue){
                if(carQueue.isEmpty()){
                    view.updateUI(NotifierType.worker, workerID, new UpdateValue("Waiting for request"));
                    try {
                        carQueue.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    continue;
                }else{
                    task = carQueue.remove(0);
                    view.updateUI(NotifierType.workersPool, 0, new UpdateValue(carQueue.size()));
                }
            }
            view.updateUI(NotifierType.worker, workerID, new UpdateValue("Waiting for components"));
            Car car = task.assembler.assembleCar();
            view.updateUI(NotifierType.worker, workerID, new UpdateValue("Assembling"));
            try {
                sleep(period);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            pool.producedCar();
            view.updateUI(NotifierType.worker, workerID, new UpdateValue("Assembled"));
            storage.putCar(car, task.askedDealer);
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
