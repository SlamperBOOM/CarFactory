package factory.storages.carStorage;

import factory.UI.Notifier;
import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.dealers.Dealer;
import factory.details.Car;
import factory.storages.StorageType;
import threadPool.workerPool.WorkerPool;

import java.util.*;

public class CarStorage{
    private final List<Car> cars;
    private final List<Dealer> dealersQueue;
    private WorkerPool workers;
    private int maxSize;
    private int carID = 0;
    private CarStorageThread thread;
    View view;

    public CarStorage(int size, View ui){
        this.maxSize = size;
        view = ui;
        cars = new ArrayList<>();
        thread = new CarStorageThread(this);
        dealersQueue = new ArrayList<>();
    }

    public void setWorkers(WorkerPool workers){
        this.workers = workers;
    }

    public synchronized void askForCar(Dealer dealer){
        workers.assembleCar();
        synchronized (dealersQueue) {
            dealersQueue.add(dealer);
        }
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
        synchronized (cars) {
            cars.add(car);
        }
        view.updateUI(new Notifier(NotifierType.storage, StorageType.car, new UpdateValue(getCount())));
        notifyAll();
    }

    public CarItem getCar(){
        synchronized (cars) {
            if(cars.size() < 1){
                return null;
            }else {
                Car car = cars.remove(0);
                Dealer targetDealer;
                synchronized (dealersQueue) {
                    targetDealer = dealersQueue.remove(0);
                }
                view.updateUI(new Notifier(NotifierType.storage, StorageType.car, new UpdateValue(getCount())));
                return new CarItem(targetDealer, car);
            }
        }
    }

    public void startMonitoring(){
        thread.start();
    }

    public void stop(){
        thread.setStopped();
    }

    public int getCount() {
        synchronized (cars) {
            return cars.size();
        }
    }
}
