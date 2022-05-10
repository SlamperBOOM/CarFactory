package factory.storages.carStorage;

import factory.dealers.Dealer;
import factory.details.Car;

public class CarItem {
    Dealer dealer;
    Car car;

    public CarItem(Dealer dealer, Car car){
        this.dealer = dealer;
        this.car = car;
    }
}
