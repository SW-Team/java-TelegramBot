package milk.telegram.type.user;

import org.json.JSONObject;

public class ChatMember{

    private final User user;
    private final String status;

    private ChatMember(JSONObject object){
        this.user = User.create(object.getJSONObject("user"));
        this.status = object.getString("status");
    }

    public User getUser(){
        return user;
    }

    public String getStatus(){
        return status;
    }

}
