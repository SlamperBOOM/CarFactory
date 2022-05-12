package factory.dealers;

import factory.details.Car;
import factory.storages.carStorage.CarStorage;

import java.util.Date;

public class Dealer {
    private CarStorage storage;
    int ID;

    public Dealer(CarStorage storage, int ID){
        this.storage = storage;
        this.ID = ID;
    }

    public synchronized void askForCar(){
        storage.askForCar(this);
    }

    public synchronized void sellCar(Car car){
        Date date = new Date();
        DealerLogger.logDeal(date + ": Dealer <" + ID + "> sold car: Car ID: <" + car.getID() + ">, " + car.getComponentsID());
    }
}
