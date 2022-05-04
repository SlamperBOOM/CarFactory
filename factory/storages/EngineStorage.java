package factory.storages;

import factory.details.Engine;

import java.util.*;

public class EngineStorage{
    List<Engine> engines;
    int size;


    public EngineStorage(int size){
        this.size = size;
        engines = new ArrayList<>();
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
    }

    public int getCount() {
        return engines.size();
    }
}
