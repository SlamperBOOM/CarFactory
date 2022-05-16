package factory.storages;

import factory.UI.Notifier;
import factory.UI.UpdateValue;
import factory.UI.View;
import factory.UI.NotifierType;
import factory.details.Body;

import java.util.ArrayList;
import java.util.List;

public class BodyStorage{
    List<Body> bodies;
    int size;
    View view;

    public BodyStorage(int size, View ui){
        this.size = size;
        bodies = new ArrayList<>();
        view = ui;
    }

    public synchronized Body getBody(){
        while(bodies.size() < 1) {
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Body body = bodies.get(0);
        bodies.remove(0);
        notify();
        view.updateUI(new Notifier(NotifierType.storage, StorageType.body, new UpdateValue(bodies.size())));
        return body;
    }

    public synchronized void putBody(Body body){
        while(bodies.size() >= size){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        bodies.add(body);
        notify();
        view.updateUI(new Notifier(NotifierType.storage, StorageType.body, new UpdateValue(bodies.size())));
    }
}
