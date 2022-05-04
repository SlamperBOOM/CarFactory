package threadPool;

import factory.assemble.Worker;
import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.CarStorage;
import factory.storages.EngineStorage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerPool {

    BodyStorage bodyStorage;
    EngineStorage engineStorage;
    AccessoryStorage accessoryStorage;
    CarStorage carStorage;

    ExecutorService executor;

    public WorkerPool(int workersCount, BodyStorage bodyStorage, EngineStorage engineStorage, AccessoryStorage accessoryStorage, CarStorage carStorage){
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.carStorage = carStorage;

        executor = Executors.newFixedThreadPool(workersCount);
    }

    public void assembleCar(){
        Runnable task = new WorkerThread(new Worker(bodyStorage, engineStorage, accessoryStorage), carStorage);
        executor.submit(task);
    }
}
