package milk.telegram.type.message;

import milk.telegram.type.interfaces.Textable;
import org.json.JSONObject;

import java.util.ArrayList;

public class TextMessage extends Message implements Textable{

    private final String text;
    private final ArrayList<MessageEntity> entities = new ArrayList<>();

    protected TextMessage(JSONObject object){
        super(object);
        this.text = object.getString("text");
        object.getJSONArray("entities").forEach(obj -> this.entities.add(MessageEntity.create((JSONObject) obj)));
    }

    public String getText(){
        return this.text;
    }

    public ArrayList<MessageEntity> getEntities(){
        return this.entities;
    }

}
