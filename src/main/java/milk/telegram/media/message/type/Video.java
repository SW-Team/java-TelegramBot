package milk.telegram.media.message.type;

import org.json.JSONObject;

public class Video{

    private Video(JSONObject object){

    }

    public static Video create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Video(object);
    }

}
