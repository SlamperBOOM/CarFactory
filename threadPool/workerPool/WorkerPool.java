package threadPool.workerPool;

import factory.UI.View;
import factory.assemble.Assembler;
import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.CarStorage;
import factory.storages.EngineStorage;

import java.util.*;

public class WorkerPool {
    private BodyStorage bodyStorage;
    private EngineStorage engineStorage;
    private AccessoryStorage accessoryStorage;
    private CarStorage carStorage;
    private int period;

    private Set<Worker> availableThread;
    private final List<Assembler> carQueue;

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
            availableThread.add(new Worker(this.carStorage, carQueue, i+1, view));
        }
    }

    public void start(){
        for (Worker worker : availableThread) {
            worker.start();
        }
    }

    public void assembleCar(){
        synchronized (carQueue){
            carQueue.add(new Assembler(bodyStorage, engineStorage, accessoryStorage));
            carQueue.notify();
        }
    }

    public void setPeriod(int period){
        this.period = period;
        for (Worker worker : availableThread) {
            worker.setPeriod(period);
        }
    }
}
