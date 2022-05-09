package factory.storages;

import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.details.Car;
import threadPool.workerPool.WorkerPool;

import java.util.ArrayList;
import java.util.List;

public class CarStorage{
    private List<Car> cars;
    private List<Integer> dealersQueue;
    private WorkerPool workers;
    private int maxSize;
    private int carID = 0;
    View view;

    public CarStorage(int size, View ui){
        this.maxSize = size;
        view = ui;
        cars = new ArrayList<>();
        dealersQueue = new ArrayList<>();
    }

    public void setWorkers(WorkerPool workers){
        this.workers = workers;
    }

    public synchronized Car getCar(int dealerID){
        workers.assembleCar();
        dealersQueue.add(dealerID);
        while(!(cars.size() > 0 /*&& dealersQueue.get(0) == dealerID*/)) {
           try{
               wait();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }
        Car car = cars.remove(0);
        dealersQueue.remove(0);
        view.updateUI(NotifierType.storage, StorageType.car, new UpdateValue(cars.size()));
        notify();
        return car;
    }

    public synchronized void putCar(Car car){
        while(cars.size() >= maxSize) {
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        car.setID(carID);
        carID++;
        cars.add(car);
        view.updateUI(NotifierType.storage, StorageType.car, new UpdateValue(cars.size()));
        notify();
    }

    public int getCount() {
        return cars.size();
    }
}
