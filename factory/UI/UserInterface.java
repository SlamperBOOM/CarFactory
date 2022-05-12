package factory.UI;

import factory.Closer;
import factory.PeriodSetter;
import factory.storages.StorageType;
import factory.suppliers.SupplierType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    Closer closer;

    public UserInterface(){
        window = new JFrame("Manufacture");
    }

    public void setSetter(PeriodSetter setter){
        this.setter = setter;
    }

    public void setCloser(Closer closer){
        this.closer = closer;
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
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
                super.windowClosing(e);
                System.exit(0);
            }
        });
        window.setResizable(false);
        window.pack();
    }

    private JPanel createSuppliersPane(SupplierType type){
        JPanel supplierPane = new JPanel();
        supplierPane.setLayout(new BoxLayout(supplierPane, BoxLayout.Y_AXIS));
        switch (type){
            case body -> {
                JTextField label = new JTextField("Body supplier", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                label.setEditable(false);
                supplierPane.add(label);

                bodySupplierSummary = new JTextField("Bodies produced: 0");
                bodySupplierSummary.setEditable(false);
                supplierPane.add(bodySupplierSummary);

                JSpinner spinner = new JSpinner(new SpinnerNumberModel(120, 10, 300, 10));
                spinner.addChangeListener(this);
                spinner.setName("BodySupplier");
                supplierPane.add(spinner);

                bodySupplierProductivity = new JTextField("Productivity: 120 bodies/min");
                bodySupplierProductivity.setEditable(false);
                supplierPane.add(bodySupplierProductivity);
            }
            case engine -> {
                JTextField label = new JTextField("Engine supplier", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                label.setEditable(false);
                supplierPane.add(label);

                engineSupplierSummary = new JTextField("Engines produced: 0");
                engineSupplierSummary.setEditable(false);
                supplierPane.add(engineSupplierSummary);

                JSpinner spinner = new JSpinner(new SpinnerNumberModel(120, 10, 300, 10));
                spinner.addChangeListener(this);
                spinner.setName("EngineSupplier");
                supplierPane.add(spinner);

                engineSupplierProductivity = new JTextField("Productivity: 120 engines/min");
                engineSupplierProductivity.setEditable(false);
                supplierPane.add(engineSupplierProductivity);
            }
            case accessory -> {
                JTextField label = new JTextField("Accessory suppliers", 10);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                label.setEditable(false);
                supplierPane.add(label);

                accessorySupplierSummary = new JTextField("Accessories produced: 0");
                accessorySupplierSummary.setEditable(false);
                supplierPane.add(accessorySupplierSummary);

                JSpinner spinner = new JSpinner(new SpinnerNumberModel(120, 10, 300, 10));
                spinner.addChangeListener(this);
                spinner.setName("AccessorySupplier");
                supplierPane.add(spinner);

                accessorySupplierProductivity = new JTextField("Productivity of each: 60 accessories/min");
                accessorySupplierProductivity.setEditable(false);
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
                label.setEditable(false);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                carStorageView = new JTextField("Cars: 0");
                carStorageView.setEditable(false);
                storagePanel.add(carStorageView);
            }
            case body -> {
                JTextField label = new JTextField("Body storage", 10);
                label.setEditable(false);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                bodyStorageView = new JTextField("Bodies: 0");
                bodyStorageView.setEditable(false);
                storagePanel.add(bodyStorageView);
            }
            case engine -> {
                JTextField label = new JTextField("Engine storage", 10);
                label.setEditable(false);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                engineStorageView = new JTextField("Engines: 0");
                engineStorageView.setEditable(false);
                storagePanel.add(engineStorageView);
            }
            case accessory -> {
                JTextField label = new JTextField("Accessory storage", 15);
                label.setEditable(false);
                label.setFont(new Font("TimesRoman", Font.BOLD, 18));
                storagePanel.add(label);
                accessoryStorageView = new JTextField("Accessories: 0");
                accessoryStorageView.setEditable(false);
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
        label.setEditable(false);
        label.setFont(new Font("TimesRoman", Font.BOLD, 18));
        workerPoolTop.add(label);

        workersQueue = new JTextField("Cars in queue: 0");
        workersQueue.setEditable(false);
        workersQueue.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        workerPoolTop.add(workersQueue);

        workersProduced = new JTextField("Cars produced: 0");
        workersProduced.setEditable(false);
        workersProduced.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        workerPoolTop.add(workersProduced);

        workerPoolTop.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        workersPanel.add(workerPoolTop);

        workersView = new ArrayList<>();
        for(int i=0; i<workersCount; ++i){
            JTextField worker = new JTextField("Waiting", 20);
            worker.setEditable(false);
            workersView.add(worker);
            workersPanel.add(worker);
        }

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(60, 10, 180, 10));
        spinner.addChangeListener(this);
        spinner.setName("Worker");
        workersPanel.add(spinner);
        workerProductivity = new JTextField("Productivity: 60 cars/min");
        workerProductivity.setEditable(false);
        workersPanel.add(workerProductivity);
        workersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return workersPanel;
    }

    private JPanel createDealersPane(int dealersCount){
        JPanel dealersPanel = new JPanel();
        dealersPanel.setLayout(new BoxLayout(dealersPanel, BoxLayout.Y_AXIS));
        JTextField label = new JTextField("Dealers");
        label.setEditable(false);
        label.setFont(new Font("TimesRoman", Font.BOLD, 18));
        dealersPanel.add(label);

        dealersView = new ArrayList<>();
        for(int i=0; i<dealersCount; ++i){
            JTextField dealer = new JTextField("Waiting", 20);
            dealer.setEditable(false);
            dealersView.add(dealer);
            dealersPanel.add(dealer);
        }

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(60, 10, 120, 10));
        spinner.addChangeListener(this);
        spinner.setName("Dealer");
        dealersPanel.add(spinner);

        dealerProductivity = new JTextField("Productivity: 60 sellings/min");
        dealerProductivity.setEditable(false);
        dealersPanel.add(dealerProductivity);
        dealersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return dealersPanel;
    }

    @Override
    public synchronized void updateUI(NotifierType notifier, int ID, UpdateValue obj) {
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
        notifyAll();
        window.pack();
    }

    @Override
    public synchronized void updateUI(NotifierType notifier, StorageType storage, UpdateValue obj) {
        switch (storage){
            case car -> carStorageView.setText("Cars: " + obj.value);
            case accessory -> accessoryStorageView.setText("Accessories: "+obj.value);
            case body -> bodyStorageView.setText("Bodies: " + obj.value);
            case engine -> engineStorageView.setText("Engines: "+obj.value);
        }
        notifyAll();
        window.pack();
    }

    @Override
    public synchronized void updateUI(NotifierType notifier, SupplierType supplier, UpdateValue obj) {
        switch (supplier){
            case body -> bodySupplierSummary.setText("Bodies produced: " + obj.value);
            case engine -> engineSupplierSummary.setText("Engines produced: " + obj.value);
            case accessory -> accessorySupplierSummary.setText("Accessories produced: " + obj.value);
        }
        notifyAll();
        window.pack();
    }

    public void exit(){
        closer.stop();
    }

    @Override
    public synchronized void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner)e.getSource();
        int productivity = (int)spinner.getValue();
        int period = 60 * 1000 / productivity;
        switch (spinner.getName()){
            case "Dealer" -> {
                setter.setDealerPeriod(period);
                dealerProductivity.setText("Productivity: " + productivity + " sellings/min");
            }
            case "Worker" ->{
                setter.setWorkerPeriod(period);
                workerProductivity.setText("Productivity: " + productivity + " cars/min");
            }
            case "BodySupplier" -> {
                setter.setBodySupplierPeriod(period);
                bodySupplierProductivity.setText("Productivity: " + productivity + " bodies/min");
            }
            case "EngineSupplier" -> {
                setter.setEngineSupplierPeriod(period);
                engineSupplierProductivity.setText("Productivity: " + productivity + " engines/min");
            }
            case "AccessorySupplier" -> {
                setter.setAccessorySupplierPeriod(period);
                accessorySupplierProductivity.setText("Productivity: " + productivity + " accessories/min");
            }
        }
        notifyAll();
    }
}
