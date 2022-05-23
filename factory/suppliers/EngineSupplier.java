package factory.suppliers;

import UI.Notifier;
import UI.NotifierType;
import UI.UpdateValue;
import UI.View;
import factory.details.Engine;
import factory.storages.EngineStorage;

public class EngineSupplier extends Thread{
    private EngineStorage storage;
    private View view;
    private int ID = 0;
    private int period = 500;
    private boolean isRunning = true;

    public EngineSupplier(EngineStorage storage, View ui){
        this.storage = storage;
        view = ui;
    }

    @Override
    public void run(){
        while(isRunning){
            try {
                sleep(period);
                storage.putEngine(new Engine(ID));
                ID++;
                view.updateUI(new Notifier(NotifierType.supplier, SupplierType.engine, new UpdateValue(ID)));
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
