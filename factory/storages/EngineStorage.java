package factory.storages;

import UI.Notifier;
import UI.UpdateValue;
import UI.View;
import UI.NotifierType;
import factory.details.Engine;

import java.util.*;

public class EngineStorage{
    List<Engine> engines;
    int size;
    View view;

    public EngineStorage(int size, View ui){
        this.size = size;
        engines = new ArrayList<>();
        view = ui;
    }

    public synchronized Engine getEngine(){
        while (engines.size() < 1) {
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        Engine engine = engines.get(0);
        engines.remove(0);
        notify();
        view.updateUI(new Notifier(NotifierType.storage, StorageType.engine, new UpdateValue(engines.size())));
        return engine;
    }

    public synchronized void putEngine(Engine engine){
        while(engines.size() >= size){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        engines.add(engine);
        notify();
        view.updateUI(new Notifier(NotifierType.storage, StorageType.engine, new UpdateValue(engines.size())));
    }
}
