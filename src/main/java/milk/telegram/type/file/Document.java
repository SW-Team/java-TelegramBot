package milk.telegram.type.file;

import org.json.JSONObject;

public class Document extends InputFile{

    private Document(JSONObject object){

    }

    public static Document create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Document(object);
    }

}
