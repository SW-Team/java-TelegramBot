package milk.telegram.media.message;

import milk.telegram.media.message.type.Video;
import org.json.JSONObject;

public class VideoMessage extends Message{

    private Video video;

    public VideoMessage(JSONObject object){
        super(object);
        this.video = new Video(object.getJSONObject("video"));
    }

    public Video getVideo(){
        return this.video;
    }

}
