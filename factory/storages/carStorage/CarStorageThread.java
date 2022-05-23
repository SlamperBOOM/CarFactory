package factory.storages.carStorage;

public class CarStorageThread extends Thread{
    private final CarStorage storage;
    private boolean isRunning = true;

    public CarStorageThread(CarStorage storage){
        this.storage = storage;
    }

    @Override
    public void run(){
        CarItem item;
        while(isRunning){
            try {
                sleep(50);
            }catch (InterruptedException e){

            }
            synchronized (storage) {
                if(storage.getCount() < 1) {
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                item = storage.getCar();
            }
            if(item != null) {
                item.dealer.sellCar(item.car);
            }
        }
    }

    public void setStopped(){
        isRunning = false;
    }
}
