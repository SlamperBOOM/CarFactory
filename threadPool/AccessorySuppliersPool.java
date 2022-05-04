package threadPool;

import factory.storages.AccessoryStorage;
import factory.suppliers.AccessorySupplier;

import java.util.ArrayList;
import java.util.List;

public class AccessorySuppliersPool {
    List<AccessorySupplier> suppliers;
    AccessoryStorage storage;

    public AccessorySuppliersPool(AccessoryStorage storage, int suppliersCount){
        this.storage = storage;
        createThreads(suppliersCount);
    }

    private void createThreads(int size){
        suppliers = new ArrayList<>();
        for(int i=0; i< size; ++i){
            suppliers.add(new AccessorySupplier(storage));
        }
    }

    public void setPeriod(int period){
        for(AccessorySupplier supplier:suppliers){
            supplier.setPeriod(period);
        }
    }

    public void start(){
        for(AccessorySupplier supplier:suppliers){
            supplier.start();
        }
    }
}
