package milk.telegram.type.message;

import milk.telegram.type.interfaces.Textable;
import org.json.JSONObject;

public class TextMessage extends Message implements Textable{

    private String text;

    public TextMessage(JSONObject object){
        super(object);
        this.text = object.getString("text");
    }

    public String getText(){
        return this.text;
    }

}
