package milk.telegram.type.game;

import org.json.JSONObject;

public class Animation{

    private Animation(JSONObject object){

    }

    public static Animation create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Animation(object);
    }

}
