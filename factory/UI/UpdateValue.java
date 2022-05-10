package factory.UI;

public class UpdateValue {
    String text = null;
    Long value = null;

    public UpdateValue(long value){
        this.value = value;
    }

    public UpdateValue(String text){
        this.text = text;
    }

    public UpdateValue(String text, long value){
        this.text = text;
        this.value = value;
    }
}
