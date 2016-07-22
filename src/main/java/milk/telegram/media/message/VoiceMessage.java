package milk.telegram.media.message;

import milk.telegram.media.message.type.Voice;
import org.json.JSONObject;

public class VoiceMessage extends Message{

    private Voice voice;

    public VoiceMessage(JSONObject object){
        super(object);
        this.voice = new Voice(object.getJSONObject("voice"));
    }

    public Voice getVoice(){
        return this.voice;
    }

}
