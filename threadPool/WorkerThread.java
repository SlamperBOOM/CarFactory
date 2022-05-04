package threadPool;

import factory.assemble.Worker;
import factory.details.Car;
import factory.storages.CarStorage;

import static java.lang.Thread.sleep;

public class WorkerThread implements Runnable{
    Worker worker;
    CarStorage storage;

    public WorkerThread(Worker worker, CarStorage storage){
        this.worker = worker;
        this.storage = storage;
    }

    @Override
    public void run(){
        try {
            sleep(1000);
            Car car = worker.assembleCar();
            storage.putCar(car);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
