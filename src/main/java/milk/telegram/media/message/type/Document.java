package milk.telegram.media.message.type;

import org.json.JSONObject;

public class Document{

    private Document(JSONObject object){

    }

    public static Document create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Document(object);
    }

}
