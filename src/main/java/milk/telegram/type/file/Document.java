package milk.telegram.type.file;

import milk.telegram.type.file.photo.PhotoSize;
import milk.telegram.type.interfaces.Idable;
import org.json.JSONObject;

public class Document implements Idable<String>{

    private final String file_id;

    private final PhotoSize thumb;

    private Document(JSONObject object){
        this.file_id = object.getString("file_id");
        this.thumb = PhotoSize.create(object.optJSONObject("thumb"));
    }

    public static Document create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Document(object);
    }

    public String getId(){
        return file_id;
    }

    public PhotoSize getThumb(){
        return thumb;
    }

}
