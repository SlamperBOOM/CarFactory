package factory.suppliers;

import factory.details.Accessory;
import factory.storages.AccessoryStorage;
import threadPool.AccessorySuppliersPool;

public class AccessorySupplier extends Thread{
    AccessoryStorage storage;
    int period = 1000;
    AccessorySuppliersPool pool;

    public AccessorySupplier(AccessoryStorage storage, AccessorySuppliersPool pool){
        this.storage = storage;
        this.pool = pool;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(period);
                storage.putAccessory(new Accessory());
                pool.add();
            }catch (InterruptedException e){

            }
        }
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
