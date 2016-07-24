package milk.telegram.media.chat;

import milk.telegram.media.interfaces.Usernamed;
import org.json.JSONObject;

public class SuperGroup extends Group implements Usernamed{

    private String username;

    public SuperGroup(JSONObject object){
        super(object);

        this.username = object.optString("username", null);
    }

    public String getUsername(){
        return this.username;
    }

}