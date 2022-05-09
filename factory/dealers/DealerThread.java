package factory.dealers;

import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;

public class DealerThread extends Thread{
    Dealer dealer;
    int period = 1000;
    View view;

    public DealerThread(Dealer dealer, View ui){
        this.dealer = dealer;
        view = ui;
    }

    @Override
    public void run(){
        while(true){
            try {
                view.updateUI(NotifierType.dealer, dealer.ID, new UpdateValue("Selling"));
                sleep(period);
                view.updateUI(NotifierType.dealer, dealer.ID, new UpdateValue("Waiting for car"));
                dealer.sellCar();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
