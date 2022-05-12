package factory;

import factory.UI.View;
import factory.dealers.DealerLogger;
import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.carStorage.CarStorage;
import factory.storages.EngineStorage;
import factory.suppliers.BodySupplier;
import factory.suppliers.EngineSupplier;
import threadPool.AccessorySuppliersPool;
import threadPool.DealerPool;
import threadPool.workerPool.WorkerPool;

import java.io.*;
import java.util.*;

public class Factory implements PeriodSetter, Closer{
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

    View view;

    public Factory(View ui){
        view = ui;
    }

    public int initFactory(){
        try(Reader reader = new InputStreamReader(new FileInputStream("src/factory/config.txt"))){
            config.load(reader);
        }catch (IOException e){
            e.printStackTrace();
            return 1;
        }

        bodyStorage = new BodyStorage(Integer.parseInt(config.get("BodyStorageSize").toString()), view);
        engineStorage = new EngineStorage(Integer.parseInt(config.get("EngineStorageSize").toString()), view);
        accessoryStorage = new AccessoryStorage(Integer.parseInt(config.get("AccessoryStorageSize").toString()), view);
        carStorage = new CarStorage(Integer.parseInt(config.get("CarStorageSize").toString()), view);

        bodySupplier = new BodySupplier(bodyStorage, view);
        engineSupplier = new EngineSupplier(engineStorage, view);
        accessorySuppliers = new AccessorySuppliersPool(accessoryStorage, Integer.parseInt(config.get("AccessorySuppliers").toString()), view);

        workers = new WorkerPool(
                Integer.parseInt(config.get("Workers").toString()), bodyStorage, engineStorage,
                accessoryStorage, carStorage, view);
        workers.setPeriod(1000);
        carStorage.setWorkers(workers);

        dealers = new DealerPool(carStorage, Integer.parseInt(config.get("Dealers").toString()), view);
        dealers.setPeriod(500);

        if(config.get("LogSale").equals("true")){
            DealerLogger.openLogger();
        }
        return 0;
    }

    public int getWorkersCount(){
        return Integer.parseInt(config.get("Workers").toString());
    }

    public int getDealersCount(){
        return Integer.parseInt(config.get("Dealers").toString());
    }

    public void start(){
        bodySupplier.start();
        engineSupplier.start();
        accessorySuppliers.start();
        workers.start();
        carStorage.startMonitoring();
        dealers.start();
    }

    @Override
    public void stop(){
        carStorage.stop();
        workers.stop();
        dealers.stop();
        accessorySuppliers.stop();
        bodySupplier.setStopped();
        engineSupplier.setStopped();
        if(config.get("LogSale").equals("true")) {
            DealerLogger.closeLogger();
        }
    }

    @Override
    public void setWorkerPeriod(int period) {
        workers.setPeriod(period);
    }

    @Override
    public void setDealerPeriod(int period) {
        dealers.setPeriod(period);
    }

    @Override
    public void setBodySupplierPeriod(int period) {
        bodySupplier.setPeriod(period);
    }

    @Override
    public void setEngineSupplierPeriod(int period) {
        engineSupplier.setPeriod(period);
    }

    @Override
    public void setAccessorySupplierPeriod(int period) {
        accessorySuppliers.setPeriod(period);
    }
}
