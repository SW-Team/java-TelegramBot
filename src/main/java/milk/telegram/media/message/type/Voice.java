package milk.telegram.media.message.type;

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
