package threadPool.workerPool;

import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.assemble.Assembler;
import factory.details.Car;
import factory.storages.CarStorage;

import java.util.List;

public class Worker extends Thread{
    private final CarStorage storage;
    private final List<Assembler> carQueue;
    private int period;
    private final int workerID;
    View view;

    public Worker(CarStorage storage, List<Assembler> carQueue, int ID, View ui){
        this.storage = storage;
        this.carQueue = carQueue;
        workerID = ID;
        view = ui;
    }

    @Override
    public void run(){
        Assembler assembler;
        while(true){
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
                    assembler = carQueue.remove(0);
                }
            }
            view.updateUI(NotifierType.worker, workerID, new UpdateValue("Waiting for components"));
            Car car = assembler.assembleCar();
            view.updateUI(NotifierType.worker, workerID, new UpdateValue("Assembling"));
            try {
                sleep(period);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            view.updateUI(NotifierType.worker, workerID, new UpdateValue("Assembled"));
            storage.putCar(car);
        }
    }


    public void setPeriod(int period){
        this.period = period;
    }

    public int getPeriod(){
        return period;
    }
}
