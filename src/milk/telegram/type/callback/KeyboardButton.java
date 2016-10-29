package milk.telegram.type.callback;

import org.json.JSONObject;

public class KeyboardButton extends JSONObject{

    public KeyboardButton setText(String text){
        this.put("text", text);
        return this;
    }

    public KeyboardButton setRequestContact(boolean request_contact){
        this.put("request_contact", request_contact);
        return this;
    }

    public KeyboardButton setRequestLocation(boolean request_location){
        this.put("request_location", request_location);
        return this;
    }

}
