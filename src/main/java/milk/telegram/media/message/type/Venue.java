package milk.telegram.media.message.type;

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
