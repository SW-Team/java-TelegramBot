package milk.telegram.type.message;

import milk.telegram.type.Textable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TextMessage extends Message implements Textable{

    private final String text;
    private final ArrayList<MessageEntity> entities;

    protected TextMessage(JSONObject object){
        super(object);
        this.text = object.getString("text");
        JSONArray array = object.optJSONArray("entities");
        if(array != null){
            this.entities = new ArrayList<>();
            array.forEach(obj -> this.entities.add(MessageEntity.create((JSONObject) obj)));
        }else{
            this.entities = null;
        }
    }

    public String getText(){
        return this.text;
    }

    public ArrayList<MessageEntity> getEntities(){
        return this.entities;
    }

    public String getName(){
        return "문자 메시지";
    }

}
