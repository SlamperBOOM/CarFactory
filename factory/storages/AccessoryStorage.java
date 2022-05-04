package factory.storages;

import factory.details.Accessory;

import java.util.*;

public class AccessoryStorage{
    List<Accessory> accessories;
    int size;
    int ID;

    public AccessoryStorage(int size){
        this.size = size;
        accessories = new ArrayList<>();
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
    }

    public int getCount() {
        return accessories.size();
    }
}
