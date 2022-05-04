package factory;

import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.CarStorage;
import factory.storages.EngineStorage;
import factory.suppliers.BodySupplier;
import factory.suppliers.EngineSupplier;
import threadPool.AccessorySuppliersPool;
import threadPool.DealerPool;
import threadPool.WorkerPool;

import java.io.*;
import java.util.*;

public class Factory {
    Properties config = new Properties();
    BodyStorage bodyStorage;
    EngineStorage engineStorage;
    AccessoryStorage accessoryStorage;
    CarStorage carStorage;

    BodySupplier bodySupplier;
    EngineSupplier engineSupplier;
    AccessorySuppliersPool accessorySuppliers;

    WorkerPool workers;
    DealerPool dealers;

    public int initFactory(){
        try(Reader reader = new InputStreamReader(new FileInputStream("src/factory/config.txt"))){
            config.load(reader);
        }catch (IOException e){
            e.printStackTrace();
            return 1;
        }

        bodyStorage = new BodyStorage(Integer.parseInt(config.get("BodyStorageSize").toString()));
        engineStorage = new EngineStorage(Integer.parseInt(config.get("EngineStorageSize").toString()));
        accessoryStorage = new AccessoryStorage(Integer.parseInt(config.get("AccessoryStorageSize").toString()));
        carStorage = new CarStorage(Integer.parseInt(config.get("CarStorageSize").toString()));

        bodySupplier = new BodySupplier(bodyStorage);
        engineSupplier = new EngineSupplier(engineStorage);
        accessorySuppliers = new AccessorySuppliersPool(accessoryStorage, Integer.parseInt(config.get("AccessorySuppliers").toString()));

        workers = new WorkerPool(Integer.parseInt(config.get("Workers").toString()), bodyStorage, engineStorage, accessoryStorage, carStorage);
        carStorage.setWorkers(workers);

        dealers = new DealerPool(carStorage, Integer.parseInt(config.get("Dealers").toString()));
        return 0;
    }

    public void start(){
        bodySupplier.start();
        engineSupplier.start();
        accessorySuppliers.start();
        dealers.start();
    }
}
