package threadPool;

import factory.dealers.Dealer;

public class DealerThread extends Thread{
    Dealer dealer;
    int period = 500;

    public DealerThread(Dealer dealer){
        this.dealer = dealer;
    }

    @Override
    public void run(){
        while(true){
            try {
                sleep(period);
                dealer.sellCar();
            }catch (InterruptedException e){

            }
        }
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
