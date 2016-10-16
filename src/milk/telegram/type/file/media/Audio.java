package milk.telegram.type.file.media;

import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class Audio implements Identifier<String>{

    private final String id;

    private final int duration;

    private final String title;
    private final String performer;
    private final String mime_type;

    private final Integer size;

    private Audio(JSONObject object){
        this.id = object.getString("file_id");

        this.duration = object.getInt("duration");
        this.size = object.has("file_size") ? object.getInt("file_size") : null;

        this.title = object.optString("title");
        this.performer = object.optString("performer");
        this.mime_type = object.optString("mime_type");
    }

    public static Audio create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Audio(object);
    }

    public String getId(){
        return id;
    }

    public int getDuration(){
        return duration;
    }

    public Integer getSize(){
        return size;
    }

    public String getTitle(){
        return title;
    }

    public String getPerformer(){
        return performer;
    }

    public String getMimeType(){
        return mime_type;
    }
}
