package milk.telegram.method.other;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;

import org.json.JSONObject;

public class LeaveChat extends SendInstance{

    public LeaveChat(TelegramBot bot){
        super(bot);
    }

    public LeaveChat setChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String){
            this.put("chat_id", chat_id);
        }else if(chat_id instanceof Number){
            this.put("chat_id", ((Number) chat_id).longValue() + "");
        }
        return this;
    }

    public Boolean send(){
        JSONObject ob = bot.updateResponse("leaveChat", this);
        return ob != null && ob.has("result") ? ob.optBoolean("result") : null;
    }

}
