package milk.telegram.media.chat;

import milk.telegram.media.interfaces.Titled;
import org.json.JSONObject;

public class Group extends Chat implements Titled{

    private String title;

    public Group(JSONObject object){
        super(object);

        this.title = object.getString("title");
    }

    public String getTitle(){
        return this.title;
    }

}