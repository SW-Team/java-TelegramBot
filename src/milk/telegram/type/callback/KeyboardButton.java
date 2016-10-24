package milk.telegram.type.callback;

import org.json.JSONObject;

public class KeyboardButton extends JSONObject{

    public void setText(String text){
        this.put("text", text);
    }

    public void setRequestContact(boolean request_contact){
        this.put("request_contact", request_contact);
    }

    public void setRequestLocation(boolean request_location){
        this.put("request_location", request_location);
    }

}
