package threadPool.workerPool;

import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.UI.View;
import factory.assemble.Assembler;
import factory.dealers.Dealer;
import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.carStorage.CarStorage;
import factory.storages.EngineStorage;

import java.util.*;

public class WorkerPool {
    private BodyStorage bodyStorage;
    private EngineStorage engineStorage;
    private AccessoryStorage accessoryStorage;
    private CarStorage carStorage;

    private Set<Worker> availableThread;
    private final List<AssembleTask> carQueue;
    private long producedCars;

    private View view;

    public WorkerPool(int workersCount, BodyStorage bodyStorage, EngineStorage engineStorage,
                      AccessoryStorage accessoryStorage, CarStorage carStorage,  View ui){
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.carStorage = carStorage;
        view = ui;

        availableThread = new HashSet<>();
        carQueue = new ArrayList<>();

        for(int i=0; i< workersCount; ++i){
            availableThread.add(new Worker(this.carStorage, carQueue, i+1, view, this));
        }
    }

    public void start(){
        for (Worker worker : availableThread) {
            worker.start();
        }
    }

    public void assembleCar(Dealer dealer){
        synchronized (carQueue){
            carQueue.add(new AssembleTask(new Assembler(bodyStorage, engineStorage, accessoryStorage), dealer));
            carQueue.notify();
            view.updateUI(NotifierType.workersPool, 0, new UpdateValue(carQueue.size()));
        }
    }

    public synchronized void producedCar(){
        producedCars++;
        view.updateUI(NotifierType.workersPool, 1, new UpdateValue(producedCars));
    }

    public void setPeriod(int period){
        for (Worker worker : availableThread) {
            worker.setPeriod(period);
        }
    }
}
