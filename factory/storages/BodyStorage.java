package factory.storages;

import factory.details.Body;

import java.util.ArrayList;
import java.util.List;

public class BodyStorage{
    List<Body> bodies;
    int size;

    public BodyStorage(int size){
        this.size = size;
        bodies = new ArrayList<>();
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
    }

    public int getCount(){
        return bodies.size();
    }
}
