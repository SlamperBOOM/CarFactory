package factory.storages.carStorage;

public class CarStorageThread extends Thread{
    private CarStorage storage;

    public CarStorageThread(CarStorage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        CarItem item;
        while(true){
            synchronized (storage) {
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                item = storage.getCar();
            }
            if(item != null) {
                item.dealer.sellCar(item.car);
            }
        }
    }
}
