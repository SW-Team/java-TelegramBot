package milk.telegram.type.file.media;

import org.json.JSONObject;

public class Voice{

    private Voice(JSONObject object){

    }

    public static Voice create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Voice(object);
    }

}
