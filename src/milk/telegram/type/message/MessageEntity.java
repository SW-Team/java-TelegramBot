package milk.telegram.type.message;

import milk.telegram.type.user.User;
import org.json.JSONObject;

public class MessageEntity{

    private final String type;

    private final String url;

    private final int offset;
    private final int length;

    private final User user;

    private MessageEntity(JSONObject object){
        this.type = object.getString("type");

        this.offset = object.getInt("offset");
        this.length = object.getInt("length");

        this.url = object.optString("url");
        this.user = User.create(object.optJSONObject("user"));
    }

    public static MessageEntity create(JSONObject object){
        if(object == null){
            return null;
        }
        return new MessageEntity(object);
    }

    public String getType(){
        return type;
    }

    public int getOffset(){
        return offset;
    }

    public int getLength(){
        return length;
    }

    public String getUrl(){
        return url;
    }

    public User getUser(){
        return user;
    }

}