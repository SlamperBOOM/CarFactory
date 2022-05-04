package factory;

import javax.swing.*;

public class UserInterface {
    JFrame window;

    public UserInterface(){
        window = new JFrame("Factory");
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(window, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exit(){
        window.dispose();
    }
}
