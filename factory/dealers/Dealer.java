package factory.dealers;

import factory.UI.Notifier;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.UI.View;
import factory.details.Car;
import factory.storages.carStorage.CarStorage;

import java.util.Date;

public class Dealer {
    private CarStorage storage;
    int ID;
    private View view;
    private int soldCarsCount = 0;
    private int askedCars = 0;

    public Dealer(CarStorage storage, int ID){
        this.storage = storage;
        this.ID = ID;
    }

    public void setView(View view){
        this.view = view;
    }

    public synchronized void askForCar(){
        storage.askForCar(this);
        askedCars++;
        view.updateUI(new Notifier(NotifierType.dealer, ID,
                new UpdateValue("sold " + soldCarsCount + " cars, waiting for " + (askedCars - soldCarsCount))));
    }

    public synchronized void sellCar(Car car){
        Date date = new Date();
        DealerLogger.logDeal(date.toString() + ": Dealer <" + ID + "> sold car: Car ID: <" + car.getID() + ">, " + car.getComponentsID());
        soldCarsCount++;
        view.updateUI(new Notifier(NotifierType.dealer, ID,
                new UpdateValue("sold " + soldCarsCount + " cars, waiting for " + (askedCars - soldCarsCount))));
    }
}
