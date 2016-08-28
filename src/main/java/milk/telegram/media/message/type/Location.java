package milk.telegram.media.message.type;

import org.json.JSONObject;

public class Location{

    private Location(JSONObject object){

    }

    public static Location create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Location(object);
    }

}
