package milk.telegram.type.file.photo;

import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class PhotoSize implements Identifier<String>{

    private final String id;

    private final int width;
    private final int height;

    private final Integer size;

    private PhotoSize(JSONObject object){
        this.id = object.getString("file_id");
        this.width  = object.getInt("width");
        this.height = object.getInt("height");
        this.size = object.has("file_size") ? object.getInt("file_size") : null;
    }

    public static PhotoSize create(JSONObject object){
        if(object == null){
            return null;
        }
        return new PhotoSize(object);
    }

    public String getId(){
        return id;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Integer getSize(){
        return size;
    }

}
