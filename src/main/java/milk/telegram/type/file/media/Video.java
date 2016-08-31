package milk.telegram.type.file.media;

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
