package factory.storages.carStorage;

import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.dealers.Dealer;
import factory.details.Car;
import factory.storages.StorageType;
import threadPool.workerPool.WorkerPool;

import java.util.*;

public class CarStorage{
    private final List<CarItem> cars;
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
    }

    public void setWorkers(WorkerPool workers){
        this.workers = workers;
    }

    public synchronized void askForCar(Dealer dealer){
        workers.assembleCar(dealer);
    }

    public synchronized void putCar(Car car, Dealer askedDealer){
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
            cars.add(new CarItem(askedDealer, car));
        }
        view.updateUI(NotifierType.storage, StorageType.car, new UpdateValue(getCount()));
        notifyAll();
    }

    public CarItem getCar(){
        synchronized (cars) {
            if(cars.size() < 1){
                return null;
            }else {
                CarItem item = cars.remove(0);
                view.updateUI(NotifierType.storage, StorageType.car, new UpdateValue(getCount()));
                return item;
            }
        }
    }

    public Car getCarForDealer(Dealer dealer){
        Car car = null;
        synchronized (cars) {
            for (int i = 0; i < cars.size(); ++i) {
                if (cars.get(i).dealer.equals(dealer)) {
                    car = cars.get(i).car;
                    cars.remove(i);
                    view.updateUI(NotifierType.storage, StorageType.car, new UpdateValue(getCount()));
                    break;
                }
            }
        }
        return car;
    }

    public void startMonitoring(){
        thread.start();
    }

    public int getCount() {
        synchronized (cars) {
            return cars.size();
        }
    }
}
