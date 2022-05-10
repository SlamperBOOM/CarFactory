package factory.UI;

import factory.PeriodSetter;
import factory.storages.StorageType;
import factory.suppliers.SupplierType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterface implements View, ChangeListener {
    JFrame window;
    List<JTextField> workersView;
    JTextField workersQueue;
    JTextField workersProduced;
    List<JTextField> dealersView;
    JTextField carStorageView;
    JTextField bodyStorageView;
    JTextField engineStorageView;
    JTextField accessoryStorageView;

    JTextField bodySupplierSummary;
    JTextField engineSupplierSummary;
    JTextField accessorySupplierSummary;

    JTextField dealerProductivity;
    JTextField workerProductivity;
    JTextField bodySupplierProductivity;
    JTextField engineSupplierProductivity;
    JTextField accessorySupplierProductivity;

    PeriodSetter setter;

    public UserInterface(){
        window = new JFrame("Manufacture");
    }

    public void setSetter(PeriodSetter setter){
        this.setter = setter;
    }

    public void createContentPane(int workersCount, int dealersCount){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel suppliers = new JPanel();
        suppliers.setLayout(new BoxLayout(suppliers, BoxLayout.Y_AXIS));
        suppliers.add(createSuppliersPane(SupplierType.body));
        suppliers.add(createSuppliersPane(SupplierType.engine));
        suppliers.add(createSuppliersPane(SupplierType.accessory));
        mainPanel.add(suppliers);

        JPanel componentStorages = new JPanel();
        componentStorages.setLayout(new BoxLayout(componentStorages, BoxLayout.Y_AXIS));
        componentStorages.add(createStoragePane(StorageType.body));
        componentStorages.add(createStoragePane(StorageType.engine));
        componentStorages.add(createStoragePane(StorageType.accessory));
        mainPanel.add(componentStorages);

        mainPanel.add(createWorkersPane(workersCount));

        mainPanel.add(createStoragePane(StorageType.car));

        mainPanel.add(createDealersPane(dealersCount));
        mainPanel.setVisible(true);

        window.setContentPane(mainPanel);
        window.setVisible(true);
        window.setBounds(100, 100, 500, 200);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
    }

    private JPanel createSuppliersPane(SupplierType type){
        JPanel supplierPane = new JPanel();
        supplierPane.setLayout(new BoxLayout(supplierPane, BoxLayout.Y_AXIS));
        switch (type){
            case body -> {
                JTextField label = new JTextField("Body supplier", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                supplierPane.add(label);
                bodySupplierSummary = new JTextField("Bodies produced: 0");
                supplierPane.add(bodySupplierSummary);
                JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 10, 300, 120);
                slider.setName("BodySupplier");
                slider.addChangeListener(this);
                supplierPane.add(slider);
                bodySupplierProductivity = new JTextField("Productivity: 120 items/min");
                supplierPane.add(bodySupplierProductivity);
            }
            case engine -> {
                JTextField label = new JTextField("Engine supplier", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                supplierPane.add(label);
                engineSupplierSummary = new JTextField("Engines produced: 0");
                supplierPane.add(engineSupplierSummary);
                JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 10, 300, 120);
                slider.setName("EngineSupplier");
                slider.addChangeListener(this);
                supplierPane.add(slider);
                engineSupplierProductivity = new JTextField("Productivity: 120 items/min");
                supplierPane.add(engineSupplierProductivity);
            }
            case accessory -> {
                JTextField label = new JTextField("Accessory suppliers", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                supplierPane.add(label);
                accessorySupplierSummary = new JTextField("Accessories produced: 0");
                supplierPane.add(accessorySupplierSummary);
                JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 10, 300, 60);
                slider.setName("AccessorySupplier");
                slider.addChangeListener(this);
                supplierPane.add(slider);
                accessorySupplierProductivity = new JTextField("Productivity of each: 60 items/min");
                supplierPane.add(accessorySupplierProductivity);
            }
        }
        supplierPane.setBorder(BorderFactory.createLineBorder(Color.black));
        return supplierPane;
    }

    private JPanel createStoragePane(StorageType type){
        JPanel storagePanel = new JPanel();
        storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.Y_AXIS));
        switch (type){
            case car -> {
                JTextField label = new JTextField("Car storage", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                carStorageView = new JTextField("Cars: 0");
                storagePanel.add(carStorageView);
            }
            case body -> {
                JTextField label = new JTextField("Body storage", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                bodyStorageView = new JTextField("Bodies: 0");
                storagePanel.add(bodyStorageView);
            }
            case engine -> {
                JTextField label = new JTextField("Engine storage", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                engineStorageView = new JTextField("Engines: 0");
                storagePanel.add(engineStorageView);
            }
            case accessory -> {
                JTextField label = new JTextField("Accessory storage", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                accessoryStorageView = new JTextField("Accessories: 0");
                storagePanel.add(accessoryStorageView);
            }
        }
        storagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return storagePanel;
    }

    private JPanel createWorkersPane(int workersCount){
        JPanel workersPanel = new JPanel();
        workersPanel.setLayout(new BoxLayout(workersPanel, BoxLayout.Y_AXIS));

        JPanel workerPoolTop = new JPanel();
        workerPoolTop.setLayout(new BoxLayout(workerPoolTop, BoxLayout.Y_AXIS));
        JTextField label = new JTextField("Workers");
        label.setFont(new Font("TimesRoman", Font.BOLD, 18));
        workerPoolTop.add(label);

        workersQueue = new JTextField("Cars in queue: 0");
        workersQueue.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        workerPoolTop.add(workersQueue);

        workersProduced = new JTextField("Cars produced: 0");
        workersProduced.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        workerPoolTop.add(workersProduced);

        workerPoolTop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        workersPanel.add(workerPoolTop);

        workersView = new ArrayList<>();
        for(int i=0; i<workersCount; ++i){
            JTextField worker = new JTextField("Waiting", 20);
            workersView.add(worker);
            workersPanel.add(worker);
        }

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 120, 60);
        slider.addChangeListener(this);
        slider.setName("Worker");
        workersPanel.add(slider);
        workerProductivity = new JTextField("Productivity: 60 items/min");
        workersPanel.add(workerProductivity);
        workersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return workersPanel;
    }

    private JPanel createDealersPane(int dealersCount){
        JPanel dealersPanel = new JPanel();
        dealersPanel.setLayout(new BoxLayout(dealersPanel, BoxLayout.Y_AXIS));
        JTextField label = new JTextField("Dealers");
        label.setFont(new Font("TimesRoman", Font.BOLD, 18));
        dealersPanel.add(label);
        dealersView = new ArrayList<>();
        for(int i=0; i<dealersCount; ++i){
            JTextField dealer = new JTextField("Waiting", 20);
            dealersView.add(dealer);
            dealersPanel.add(dealer);
        }
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 120, 60);
        slider.addChangeListener(this);
        slider.setName("Dealer");
        dealersPanel.add(slider);
        dealerProductivity = new JTextField("Productivity: 60 items/min");
        dealersPanel.add(dealerProductivity);
        dealersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return dealersPanel;
    }

    @Override
    public void showMessage(String message){
        JOptionPane.showMessageDialog(window, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void updateUI(NotifierType notifier, int ID, UpdateValue obj) {
        switch (notifier){
            case dealer -> {
                JTextField dealer = dealersView.get(ID - 1);
                dealer.setText("Dealer " + ID + " " + obj.text);
            }
            case worker -> {
                JTextField worker = workersView.get(ID - 1);
                worker.setText("Worker " + ID + " " + obj.text);
            }
            case workersPool -> {
                if(ID == 0) {
                    workersQueue.setText("Cars in queue: " + obj.value);
                }else{
                    workersProduced.setText("Cars produced: " + obj.value);
                }
            }
        }
        window.pack();
    }

    @Override
    public void updateUI(NotifierType notifier, StorageType storage, UpdateValue obj) {
        switch (storage){
            case car -> carStorageView.setText("Cars: " + obj.value);
            case accessory -> accessoryStorageView.setText("Accessories: "+obj.value);
            case body -> bodyStorageView.setText("Bodies: " + obj.value);
            case engine -> engineStorageView.setText("Engines: "+obj.value);
        }
        window.pack();
    }

    @Override
    public void updateUI(NotifierType notifier, SupplierType supplier, UpdateValue obj) {
        switch (supplier){
            case body -> bodySupplierSummary.setText("Bodies produced: " + obj.value);
            case engine -> engineSupplierSummary.setText("Engines produced: " + obj.value);
            case accessory -> accessorySupplierSummary.setText("Accessories produced: " + obj.value);
        }
    }

    public void exit(){
        window.dispose();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        int productivity = slider.getValue();
        int period = 60 * 1000 / productivity;
        switch (slider.getName()){
            case "Dealer" -> {
                setter.setDealerPeriod(period);
                dealerProductivity.setText("Productivity: " + productivity + " items/min");
            }
            case "Worker" ->{
                setter.setWorkerPeriod(period);
                workerProductivity.setText("Productivity: " + productivity + " items/min");
            }
            case "BodySupplier" -> {
                setter.setBodySupplierPeriod(period);
                bodySupplierProductivity.setText("Productivity: " + productivity + " items/min");
            }
            case "EngineSupplier" -> {
                setter.setEngineSupplierPeriod(period);
                engineSupplierProductivity.setText("Productivity: " + productivity + " items/min");
            }
            case "AccessorySupplier" -> {
                setter.setAccessorySupplierPeriod(period);
                accessorySupplierProductivity.setText("Productivity: " + productivity + " items/min");
            }
        }
    }
}
