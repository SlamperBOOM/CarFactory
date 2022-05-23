package factory.dealers;

import UI.View;

public class DealerThread extends Thread{
    private Dealer dealer;
    private int period = 950;
    private boolean isRunning = true;

    public DealerThread(Dealer dealer, View ui){
        this.dealer = dealer;
        this.dealer.setView(ui);
    }

    @Override
    public void run(){
        while(isRunning){
            try {
                sleep(period);
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
