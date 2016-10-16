package milk.telegram.type.file.media;

import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class Voice implements Identifier<String>{

    private final String id;
    private final String mime_type;

    private final int duration;
    private final Integer size;

    private Voice(JSONObject object){
        this.id = object.getString("file_id");
        this.mime_type = object.optString("mime_type");

        this.size = object.getInt("file_size");
        this.duration = object.getInt("duration");
    }

    public static Voice create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Voice(object);
    }

    @Override
    public String getId(){
        return id;
    }

    public int getDuration(){
        return duration;
    }

    public String getMimeType(){
        return mime_type;
    }

    public Integer getSize(){
        return size;
    }

}
