package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import org.json.JSONObject;

public class ChatMembersCountGetter extends Getter{

    protected String chat_id;

    public ChatMembersCountGetter(TelegramBot bot){
        super(bot);
    }

    public String getChatId(){
        return chat_id;
    }

    public ChatMembersCountGetter setChatId(Object chat_id){
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

    public Integer send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        JSONObject ob = bot.updateResponse("getChatMembersCount", object);
        return (ob == null || !ob.has("result")) ? null : ob.optInt("result");
    }

}
