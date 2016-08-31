package milk.telegram.type;

import milk.telegram.type.interfaces.Idable;
import milk.telegram.type.message.Message;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public class CallbackQuery implements Idable<String>{

    private final String id;
    private final User from;
    private final Message message;

    private final String data;
    private final String inline_message_id;

    private CallbackQuery(JSONObject object){
        this.id = object.getString("id");
        this.from = User.create(object.getJSONObject("from"));
        this.message = Message.create(object.optJSONObject("message"));

        this.data = object.getString("data");
        this.inline_message_id = object.optString("inline_message_id");
    }

    public static CallbackQuery create(JSONObject object){
        if(object == null){
            return null;
        }
        return new CallbackQuery(object);
    }

    public String getId(){
        return id;
    }

    public User getFrom(){
        return from;
    }

    public String getData(){
        return data;
    }

    public String getInlineId(){
        return inline_message_id;
    }

    public Message getMessage(){
        return message;
    }

}
