package milk.telegram.media.message.type;

import milk.telegram.media.interfaces.StringId;
import org.json.JSONObject;

public class Sticker implements StringId{

    private String file_id;

    private int width;
    private int height;

    private int file_size;

    private Object thumb;

    public Sticker(JSONObject object){
        this.file_id = object.getString("file_id");
        this.width = object.getInt("width");
        this.height = object.getInt("height");
        this.file_size = object.getInt("file_size");
        this.thumb = object.get("thumb");
    }

    public String getId(){
        return this.file_id;
    }

    public int getSize(){
        return this.file_size;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Object getThumb(){
        return this.thumb;
    }

}
