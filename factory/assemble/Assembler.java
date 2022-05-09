package factory.assemble;

import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Engine;
import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.EngineStorage;

import java.util.ArrayList;
import java.util.List;

public class Assembler {
    private BodyStorage bodyStorage;
    private EngineStorage engineStorage;
    private AccessoryStorage accessoryStorage;

    public Assembler(BodyStorage bodyStorage, EngineStorage engineStorage, AccessoryStorage accessoryStorage){
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
    }

    public Car assembleCar(){
        Body body = bodyStorage.getBody();
        Engine engine = engineStorage.getEngine();
        List<Accessory> accessories = new ArrayList<>();
        for(int i=0; i<3; ++i){
            accessories.add(accessoryStorage.getAccessory());
        }
        return new Car(engine, body, accessories);
    }
}
