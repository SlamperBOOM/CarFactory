package factory.assemble;

import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Engine;
import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.EngineStorage;
import factory.storages.carStorage.CarStorage;
import threadPool.Task;

import java.util.ArrayList;
import java.util.List;

public class Assembler implements Task {
    private BodyStorage bodyStorage;
    private EngineStorage engineStorage;
    private AccessoryStorage accessoryStorage;
    private CarStorage carStorage;

    public Assembler(BodyStorage bodyStorage, EngineStorage engineStorage, AccessoryStorage accessoryStorage, CarStorage carStorage){
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.carStorage = carStorage;
    }

    @Override
    public void doTask() {
        Body body = bodyStorage.getBody();
        Engine engine = engineStorage.getEngine();
        List<Accessory> accessories = new ArrayList<>();
        for(int i=0; i<3; ++i){
            accessories.add(accessoryStorage.getAccessory());
        }
        carStorage.putCar(new Car(engine, body, accessories));
    }
}
