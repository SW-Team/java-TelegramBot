package milk.telegram.media.message.type;

import org.json.JSONObject;

public class Contact{

    private Contact(JSONObject object){

    }

    public static Contact create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Contact(object);
    }

}
