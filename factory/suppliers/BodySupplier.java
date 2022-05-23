package factory.suppliers;

import UI.Notifier;
import UI.NotifierType;
import UI.UpdateValue;
import UI.View;
import factory.details.Body;
import factory.storages.BodyStorage;

public class BodySupplier extends Thread{
    private BodyStorage storage;
    private View view;
    private int period = 500;
    private int ID = 0;
    private boolean isRunning = true;

    public BodySupplier(BodyStorage storage, View ui){
        this.storage = storage;
        view = ui;
    }

    @Override
    public void run(){
        while(isRunning){
            try {
                sleep(period);
                storage.putBody(new Body(ID));
                ID++;
                view.updateUI(new Notifier(NotifierType.supplier, SupplierType.body, new UpdateValue(ID)));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setStopped(){
        isRunning = false;
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
