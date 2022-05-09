package factory.storages;

import factory.UI.UpdateValue;
import factory.UI.View;
import factory.UI.NotifierType;
import factory.details.Accessory;

import java.util.*;

public class AccessoryStorage{
    List<Accessory> accessories;
    View view;
    int size;
    int ID;

    public AccessoryStorage(int size, View ui){
        this.size = size;
        accessories = new ArrayList<>();
        view = ui;
    }

    public synchronized Accessory getAccessory(){
        while(accessories.size() < 1){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Accessory accessory = accessories.get(0);
        accessories.remove(0);
        notify();
        view.updateUI(NotifierType.storage, StorageType.accessory, new UpdateValue(accessories.size()));
        return accessory;
    }

    public synchronized void putAccessory(Accessory accessory){
        while(accessories.size() >= size){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        accessory.setID(ID);
        accessories.add(accessory);
        ID++;
        notify();
        view.updateUI(NotifierType.storage, StorageType.accessory, new UpdateValue(accessories.size()));
    }

    public int getCount() {
        return accessories.size();
    }
}
