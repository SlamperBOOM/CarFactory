package threadPool;

import factory.UI.UpdateValue;
import factory.UI.View;
import factory.UI.NotifierType;
import factory.storages.AccessoryStorage;
import factory.suppliers.AccessorySupplier;
import factory.suppliers.SupplierType;

import java.util.ArrayList;
import java.util.List;

public class AccessorySuppliersPool {
    private List<AccessorySupplier> suppliers;
    private AccessoryStorage storage;
    private int producedCount;
    private View view;

    public AccessorySuppliersPool(AccessoryStorage storage, int suppliersCount, View ui){
        this.storage = storage;
        view = ui;
        createThreads(suppliersCount);
    }

    private void createThreads(int size){
        suppliers = new ArrayList<>();
        for(int i=0; i< size; ++i){
            suppliers.add(new AccessorySupplier(storage, this));
        }
    }

    public void setPeriod(int period){
        for(AccessorySupplier supplier:suppliers){
            supplier.setPeriod(period);
        }
    }

    public synchronized void add(){
        producedCount++;
        view.updateUI(NotifierType.supplier, SupplierType.accessory, new UpdateValue(producedCount));
    }

    public void start(){
        for(AccessorySupplier supplier:suppliers){
            supplier.start();
        }
    }

    public void stop(){
        for(AccessorySupplier supplier:suppliers){
            supplier.setStopped();
        }
    }
}
