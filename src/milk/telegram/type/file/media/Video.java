package milk.telegram.type.file.media;

import milk.telegram.type.file.photo.PhotoSize;
import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class Video implements Identifier<String>{

    private final String id;

    private final String mime_type;

    private final int width;
    private final int height;
    private final int duration;

    private final PhotoSize thumb;

    private final Integer size;

    private Video(JSONObject object){
        this.id = object.getString("file_id");

        this.width = object.getInt("width");
        this.height = object.getInt("height");
        this.duration = object.getInt("duration");

        this.thumb = PhotoSize.create(object.optJSONObject("thumb"));
        this.mime_type = object.optString("mime_type");

        this.size = object.has("file_size") ? object.getInt("file_size") : null;
    }

    public static Video create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Video(object);
    }

    public String getId(){
        return id;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getDuration(){
        return duration;
    }

    public PhotoSize getThumb(){
        return thumb;
    }

    public Integer getSize(){
        return size;
    }

    public String getMimeYype(){
        return mime_type;
    }

}
