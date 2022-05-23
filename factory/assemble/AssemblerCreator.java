package factory.assemble;

import factory.storages.AccessoryStorage;
import factory.storages.BodyStorage;
import factory.storages.EngineStorage;
import factory.storages.carStorage.CarStorage;

public class AssemblerCreator {
    private static BodyStorage bodyStorage;
    private static AccessoryStorage accessoryStorage;
    private static EngineStorage engineStorage;

    public static Assembler createAssembleTask(CarStorage carStorage){
        return new Assembler(bodyStorage, engineStorage, accessoryStorage, carStorage);
    }

    public static void setBodyStorage(BodyStorage bodyStorage) {
        AssemblerCreator.bodyStorage = bodyStorage;
    }

    public static void setAccessoryStorage(AccessoryStorage accessoryStorage) {
        AssemblerCreator.accessoryStorage = accessoryStorage;
    }

    public static void setEngineStorage(EngineStorage engineStorage) {
        AssemblerCreator.engineStorage = engineStorage;
    }
}
