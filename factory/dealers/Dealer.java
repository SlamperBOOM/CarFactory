package factory.dealers;

import factory.details.Car;
import factory.storages.CarStorage;

public class Dealer {
    CarStorage storage;
    int ID;

    public Dealer(CarStorage storage, int ID){
        this.storage = storage;
        this.ID = ID;
    }

    public void sellCar(){
        Car car = storage.getCar(ID);
        System.out.println("Dealer " + ID + " sold car: Car ID: " + car.getID() + ", " + car.getComponentsID());
    }
}
