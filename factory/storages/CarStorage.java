package factory.storages;

import factory.details.Car;
import threadPool.WorkerPool;

import java.util.ArrayList;
import java.util.List;

public class CarStorage{
    List<Car> cars;
    WorkerPool workers;
    int maxSize;
    int ID = 0;

    public CarStorage(int size){
        this.maxSize = size;
        cars = new ArrayList<Car>();
    }

    public void setWorkers(WorkerPool workers){
        this.workers = workers;
    }

    public synchronized Car getCar(){
        while(cars.size() < 1) {
           try{
               workers.assembleCar();
               wait();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }
        Car car = cars.get(0);
        cars.remove(0);
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
        car.setID(ID);
        ID++;
        cars.add(car);
        System.out.println("Car added, ID: " + car.getID());
        System.out.println("Size: " + cars.size());
        notify();
    }

    public int getCount() {
        return cars.size();
    }
}
