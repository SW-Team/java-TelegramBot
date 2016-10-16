package milk.telegram.type.user;

import org.json.JSONObject;

public class ChatMember{

    private final User user;
    private final String status;

    private ChatMember(JSONObject object){
        this.user = User.create(object.getJSONObject("user"));
        this.status = object.getString("status");
    }

    public static ChatMember create(JSONObject object){
        if(object == null){
            return null;
        }else if(object.has("result")){
            object = object.optJSONObject("result");
            if(object.length() < 1){
                return null;
            }
        }
        return new ChatMember(object);
    }

    public User getUser(){
        return user;
    }

    public String getStatus(){
        return status;
    }

}
