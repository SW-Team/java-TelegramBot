package milk.telegram.type.file;

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
