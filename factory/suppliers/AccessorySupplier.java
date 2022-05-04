package factory.suppliers;

import factory.details.Accessory;
import factory.storages.AccessoryStorage;

public class AccessorySupplier extends Thread{
    AccessoryStorage storage;
    int period = 1000;

    public AccessorySupplier(AccessoryStorage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(period);
                storage.putAccessory(new Accessory());
            }catch (InterruptedException e){

            }
        }
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
