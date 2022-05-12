package factory.dealers;

import factory.UI.View;
import factory.UI.NotifierType;
import factory.UI.UpdateValue;

public class DealerThread extends Thread{
    private Dealer dealer;
    private int period = 950;
    private View view;
    private boolean isRunning = true;

    public DealerThread(Dealer dealer, View ui){
        this.dealer = dealer;
        view = ui;
    }

    @Override
    public void run(){
        while(isRunning){
            try {
                view.updateUI(NotifierType.dealer, dealer.ID, new UpdateValue("Selling"));
                sleep(period);
                view.updateUI(NotifierType.dealer, dealer.ID, new UpdateValue("Waiting for car"));
                dealer.askForCar();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setStopped(){
        isRunning = false;
    }

    public void setPeriod(int period){
        this.period = period;
    }
}
