package milk.telegram.type.file;

import org.json.JSONObject;

public class Venue{

    private Venue(JSONObject object){

    }

    public static Venue create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Venue(object);
    }

}
