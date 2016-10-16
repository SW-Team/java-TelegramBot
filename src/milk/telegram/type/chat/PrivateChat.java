package milk.telegram.type.chat;

import milk.telegram.type.Usernamed;
import org.json.JSONObject;

public class PrivateChat extends Chat implements Usernamed{

    private String last;
    private String first;
    private String username;

    public PrivateChat(JSONObject object){
        super(object);

        this.last = object.optString("last_name", null);
        this.first = object.optString("first_name", null);
        this.username = object.optString("username", null);
    }

    public String getFirstName(){
        return this.first;
    }

    public String getLastName(){
        return this.last;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFullName(){
        return (this.getLastName() == null ? "" : this.getLastName() + " ") + this.getFirstName();
    }

}
