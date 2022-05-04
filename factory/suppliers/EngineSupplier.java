package factory.suppliers;

import factory.details.Engine;
import factory.storages.EngineStorage;

public class EngineSupplier extends Thread{
    EngineStorage storage;
    int ID = 0;
    int period = 1000;

    public EngineSupplier(EngineStorage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(period);
                storage.putEngine(new Engine(ID));
                ID++;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
