package factory.details;

import java.util.List;

public class Car {
    Engine engine;
    Body body;
    List<Accessory> accessories;
    int ID;

    public Car(Engine engine, Body body, List<Accessory> accessories){
        this.engine = engine;
        this.body = body;
        this.accessories = accessories;
    }

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public String getComponentsID(){
        StringBuilder message = new StringBuilder("Body ID: " + body.getID());
        message.append(", Engine ID: " + engine.getID());
        for(int i=0; i<accessories.size(); ++i) {
            message.append(", Accessory " + (i+1) + " ID: " + accessories.get(i).getID());
        }
        return message.toString();
    }
}
