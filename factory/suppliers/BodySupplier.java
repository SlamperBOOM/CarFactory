package factory.suppliers;

import factory.details.Body;
import factory.storages.BodyStorage;

public class BodySupplier extends Thread{
    BodyStorage storage;
    int period = 1000;
    int ID = 0;

    public BodySupplier(BodyStorage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(period);
                storage.putBody(new Body(ID));
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
