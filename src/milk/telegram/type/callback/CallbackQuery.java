package milk.telegram.type.callback;

import milk.telegram.type.Identifier;
import milk.telegram.type.message.Message;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public class CallbackQuery implements Identifier<String>{

    private final String id;
    private final User from;
    private final Message message;

    private final String chat_instance;

    private final String data;
    private final String inline_message_id;

    private final String game_short_name;

    private CallbackQuery(JSONObject object){
        this.id = object.getString("id");
        this.from = User.create(object.getJSONObject("from"));
        this.message = Message.create(object.optJSONObject("message"));

        this.chat_instance = object.optString("chat_instance", null);

        this.data = object.optString("data", null);
        this.inline_message_id = object.optString("inline_message_id");

        this.game_short_name = object.optString("game_short_name", null);
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

    public String getGameShortName(){
        return game_short_name;
    }

    public String getChatInstance(){
        return chat_instance;
    }
}
