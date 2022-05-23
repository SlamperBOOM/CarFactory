import factory.Factory;
import UI.UserInterface;

public class Main {
    public static void main(String[] args){
        UserInterface ui = new UserInterface();
        Factory factory = new Factory(ui);
        if(factory.initFactory() == 1){
            System.err.println("Can't read config file. Exiting");
            return;
        }
        ui.createContentPane(factory.getWorkersCount(), factory.getDealersCount());
        ui.setSetter(factory);
        ui.setCloser(factory);
        factory.start();
    }
}
