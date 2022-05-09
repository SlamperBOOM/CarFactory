package factory.UI;
import factory.storages.StorageType;
import factory.suppliers.SupplierType;

public interface View {
    void showMessage(String message);
    void updateUI(NotifierType notifier, int ID, UpdateValue obj);
    void updateUI(NotifierType notifier, StorageType storage, UpdateValue obj);
    void updateUI(NotifierType notifier, SupplierType supplier, UpdateValue obj);
}
