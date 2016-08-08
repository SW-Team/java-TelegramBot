package milk.telegram.media.message;

import milk.telegram.media.interfaces.Textable;
import milk.telegram.media.message.type.PhotoSize;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoMessage extends Message implements Textable{

    private String caption;
    private ArrayList<PhotoSize> photo;

    public PhotoMessage(JSONObject object){
        super(object);

        this.caption = object.optString("caption", null);
        this.photo = new ArrayList<>();
        object.getJSONArray("photo").forEach(obj -> {
            if(obj instanceof JSONObject) this.photo.add(new PhotoSize((JSONObject) obj));
        });
    }

    public String getText(){
        return this.caption;
    }

    public ArrayList<PhotoSize> getPhotoList(){
        return this.photo;
    }

}
