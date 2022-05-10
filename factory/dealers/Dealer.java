package factory.dealers;

import factory.details.Car;
import factory.storages.carStorage.CarStorage;

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
        System.out.println("Dealer " + ID + " sold car: Car ID: " + car.getID() + ", " + car.getComponentsID());
    }
}
