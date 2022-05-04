package threadPool;

import factory.dealers.Dealer;
import factory.storages.CarStorage;

import java.util.ArrayList;
import java.util.List;

public class DealerPool {
    List<DealerThread> dealers;
    CarStorage storage;

    public DealerPool(CarStorage storage, int dealersCount){
        this.storage = storage;
        createDealers(dealersCount);
    }

    private void createDealers(int size){
        dealers = new ArrayList<>();
        for(int i=0; i<size; ++i){
            dealers.add(new DealerThread(new Dealer(storage, i+1)));
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
}
