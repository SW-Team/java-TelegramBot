package milk.telegram.type.message;

import milk.telegram.type.file.media.Audio;
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

    @Override
    public String getName(){
        return "음악";
    }

}
