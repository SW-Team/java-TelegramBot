package milk.telegram.type.message;

import milk.telegram.type.Textable;
import milk.telegram.type.file.photo.PhotoSize;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoMessage extends Message implements Textable{

    private final String caption;
    private final ArrayList<PhotoSize> photo = new ArrayList<>();

    public PhotoMessage(JSONObject object){
        super(object);

        this.caption = object.optString("caption", null);
        object.getJSONArray("photo").forEach(obj -> this.photo.add(PhotoSize.create((JSONObject) obj)));
    }

    public String getText(){
        return this.caption;
    }

    public ArrayList<PhotoSize> getPhotoList(){
        return this.photo;
    }

    public String getName(){
        return "사진";
    }

}
