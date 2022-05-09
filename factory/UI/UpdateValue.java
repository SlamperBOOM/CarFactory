package factory.UI;

public class UpdateValue {
    String text = null;
    Integer value = null;

    public UpdateValue(int value){
        this.value = value;
    }

    public UpdateValue(String text){
        this.text = text;
    }

    public UpdateValue(String text, int value){
        this.text = text;
        this.value = value;
    }
}
