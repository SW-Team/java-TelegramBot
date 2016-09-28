package milk.telegram.type.file.photo;

import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class Sticker implements Identifier<String>{

    private final String file_id;

    private final int width;
    private final int height;

    private final Integer file_size;

    private final String emoji;

    private final PhotoSize thumb;

    private Sticker(JSONObject object){
        this.file_id = object.getString("file_id");
        this.width = object.getInt("width");
        this.height = object.getInt("height");

        this.emoji = object.optString("emoji");
        this.thumb = PhotoSize.create(object.optJSONObject("thumb"));
        this.file_size = object.has("file_size") ? object.getInt("file_size") : null;
    }

    public static Sticker create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Sticker(object);
    }

    public String getId(){
        return this.file_id;
    }

    public String getEmoji(){
        return emoji;
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

    public PhotoSize getThumb(){
        return this.thumb;
    }
}
