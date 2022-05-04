import factory.Factory;
import factory.UserInterface;

public class Main {
    public static void main(String[] args){
        UserInterface ui = new UserInterface();
        Factory factory = new Factory();
        if(factory.initFactory() == 1){
            ui.showMessage("Can't read config file, exiting");
            ui.exit();
            return;
        }
        factory.start();
    }
}
