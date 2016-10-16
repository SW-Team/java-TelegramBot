package milk.telegram.type.inline;

import milk.telegram.type.message.*;
import org.json.JSONObject;

public class InlineQueryResult{

    private String id;
    private String type;

    protected InlineQueryResult(JSONObject object){
        this.id = object.getString("id");
        this.type = object.getString("type");
    }

    public static InlineQueryResult create(JSONObject object){
        if(object == null){
            return null;
        }else if(object.has("result")){
            object = object.optJSONObject("result");
            if(object.length() < 1){
                return null;
            }
        }

        /*if(object.has("audio")){
            return new AudioMessage(object);
        }else if(object.has("contact")){
            return new ContactMessage(object);
        }else if(object.has("document")){
            return new DocumentMessage(object);
        }else if(object.has("location")){
            return new LocationMessage(object);
        }else if(object.has("photo")){
            return new PhotoMessage(object);
        }else if(object.has("sticker")){
            return new StickerMessage(object);
        }else if(object.has("text")){
            return new TextMessage(object);
        }else if(object.has("venue")){
            return new VenueMessage(object);
        }else if(object.has("video")){
            return new VideoMessage(object);
        }else if(object.has("voice")){
            return new VoiceMessage(object);
        }*/
        return new InlineQueryResult(object);
    }

    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }
}
