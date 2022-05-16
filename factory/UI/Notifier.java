package factory.UI;

import factory.storages.StorageType;
import factory.suppliers.SupplierType;

public class Notifier {
    private int ID = 0;
    private NotifierType notifierType;
    private StorageType storageType = null;
    private SupplierType supplierType = null;
    private UpdateValue value;

    public Notifier(NotifierType notifierType, int ID, UpdateValue value) {
        this.ID = ID;
        this.notifierType = notifierType;
        this.value = value;
    }

    public Notifier(NotifierType notifierType, SupplierType supplierType, UpdateValue value) {
        this.notifierType = notifierType;
        this.supplierType = supplierType;
        this.value = value;
    }

    public Notifier(NotifierType notifierType, StorageType storageType, UpdateValue value) {
        this.notifierType = notifierType;
        this.storageType = storageType;
        this.value = value;
    }

    public SupplierType getSupplierType() {
        return supplierType;
    }

    public int getID() {
        return ID;
    }

    public NotifierType getNotifierType() {
        return notifierType;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public UpdateValue getValue() {
        return value;
    }
}