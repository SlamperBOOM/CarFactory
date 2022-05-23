package factory.dealers;

import UI.View;
import factory.storages.carStorage.CarStorage;

import java.util.ArrayList;
import java.util.List;

public class DealerPool {
    private List<DealerThread> dealers;
    private CarStorage storage;
    private View view;

    public DealerPool(CarStorage storage, int dealersCount, View ui){
        this.storage = storage;
        view = ui;
        createDealers(dealersCount);
    }

    private void createDealers(int size){
        dealers = new ArrayList<>();
        for(int i=0; i<size; ++i){
            dealers.add(new DealerThread(new Dealer(storage, i+1), view));
        }
    }

    public void setPeriod(int period){
        for(DealerThread dealer: dealers){
            dealer.setPeriod(period);
        }
    }

    public void start(){
        for(DealerThread dealer: dealers){
            dealer.start();
        }
    }

    public void stop(){
        for(DealerThread dealer: dealers){
            dealer.setStopped();
        }
    }
}
