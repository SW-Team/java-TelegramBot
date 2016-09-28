package milk.telegram.type.chat;

import milk.telegram.type.Titled;
import milk.telegram.type.Usernamed;
import org.json.JSONObject;

public class Channel extends Chat implements Titled, Usernamed{

    private String title;
    private String username;

    public Channel(JSONObject object){
        super(object);

        this.title = object.getString("title");
        this.username = object.optString("username", null);
    }

    public String getTitle(){
        return this.title;
    }

    public String getUsername(){
        return this.username;
    }

}
