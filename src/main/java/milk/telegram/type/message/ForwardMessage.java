package milk.telegram.type.message;

import milk.telegram.type.chat.Chat;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public class ForwardMessage extends TextMessage{

    private final User forward_from;
    private final Chat forward_from_chat;

    public ForwardMessage(JSONObject object){
        super(object);
        this.forward_from = User.create(object.optJSONObject("forward_from"));
        this.forward_from_chat = Chat.create(object.optJSONObject("forward_from_chat"));
    }

    public User getForwardFrom(){
        return forward_from;
    }

    public Chat getForwardChat(){
        return forward_from_chat;
    }
}
