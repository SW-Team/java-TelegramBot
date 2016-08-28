package milk.telegram.media.message;

import milk.telegram.media.message.type.Audio;
import org.json.JSONObject;

public class AudioMessage extends Message{

    private Audio audio;

    public AudioMessage(JSONObject object){
        super(object);
        this.audio = Audio.create(object.getJSONObject("audio"));
    }

    public Audio getAudio(){
        return audio;
    }
}
