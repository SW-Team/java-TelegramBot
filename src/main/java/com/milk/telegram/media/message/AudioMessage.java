package com.milk.telegram.media.message;

import com.milk.telegram.media.message.type.Audio;
import org.json.JSONObject;

public class AudioMessage extends Message{

    private Audio audio;

    public AudioMessage(JSONObject object){
        super(object);
        this.audio = new Audio(object.getJSONObject("audio"));
    }

    public Audio getAudio(){
        return audio;
    }
}
