package factory.suppliers;

import factory.UI.NotifierType;
import factory.UI.UpdateValue;
import factory.UI.View;
import factory.details.Body;
import factory.storages.BodyStorage;

public class BodySupplier extends Thread{
    BodyStorage storage;
    View view;
    int period = 500;
    int ID = 0;

    public BodySupplier(BodyStorage storage, View ui){
        this.storage = storage;
        view = ui;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(period);
                storage.putBody(new Body(ID));
                ID++;
                view.updateUI(NotifierType.supplier, SupplierType.body, new UpdateValue(ID));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
