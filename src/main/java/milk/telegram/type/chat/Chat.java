package milk.telegram.type.chat;

import org.json.JSONObject;
import milk.telegram.type.interfaces.Idable;

public abstract class Chat implements Idable<Integer>{

    private int id;

    private String type;

    protected Chat(JSONObject object){
        this.id = object.getInt("id");
        this.type = object.getString("type");
    }

    public static Chat create(JSONObject object){
        if(object == null){
            return null;
        }else if(object.has("result")){
            object = object.optJSONObject("result");
            if(object.length() < 1){
                return null;
            }
        }

        switch(object.getString("type")){
            case "group":
                return new Group(object);
            case "channel":
                return new Channel(object);
            case "supergroup":
                return new SuperGroup(object);
            case "private":
                return new PrivateChat(object);
        }
        return null;
    }

    public Integer getId(){
        return this.id;
    }

    public String getType(){
        return this.type;
    }

}
