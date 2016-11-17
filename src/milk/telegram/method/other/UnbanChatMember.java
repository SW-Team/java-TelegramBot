package milk.telegram.method.other;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.user.User;

import org.json.JSONObject;

public class UnbanChatMember extends SendInstance{

    public UnbanChatMember(TelegramBot bot){
        super(bot);
    }

    public UnbanChatMember setUserId(Object user_id){
        if(user_id instanceof User){
            this.put("user_id", ((User) user_id).getId());
        }else if(user_id instanceof Number){
            this.put("user_id", ((Number) user_id).intValue());
        }
        return this;
    }

    public UnbanChatMember setChatId(Object chat_id){
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
        JSONObject ob = bot.updateResponse("unbanChatMember", this);
        return ob != null && ob.has("result") ? ob.optBoolean("result") : null;
    }
}
