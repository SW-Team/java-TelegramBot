package milk.telegram.type.message;

import milk.telegram.type.file.media.Voice;
import org.json.JSONObject;

public class VoiceMessage extends Message{

    private Voice voice;

    public VoiceMessage(JSONObject object){
        super(object);
        this.voice = Voice.create(object.getJSONObject("voice"));
    }

    public Voice getVoice(){
        return this.voice;
    }

    public String getName(){
        return "음성 메시지";
    }

}
