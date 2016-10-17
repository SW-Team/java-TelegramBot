package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import org.json.JSONObject;

public class ChatActionSender extends SendInstance{

    public String action;
    public String chat_id;

    public ChatActionSender(TelegramBot bot){
        super(bot);
    }

    public String getAction(){
        return action;
    }

    public String getChatId(){
        return chat_id;
    }

    public ChatActionSender setAction(String action){
        this.action = action;
        return this;
    }

    public ChatActionSender setChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String){
            this.chat_id = (String) chat_id;
        }else if(chat_id instanceof Number){
            this.chat_id = ((Number) chat_id).longValue() + "";
        }
        return this;
    }

    public Object send(){
        JSONObject object = new JSONObject();
        object.put("action", action);
        object.put("chat_id", chat_id);
        bot.updateResponse("sendChatAction", object);
        return null;
    }

}
