package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.user.ChatMember;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public class ChatMemberGetter extends Getter{

    public ChatMemberGetter(TelegramBot bot){
        super(bot);
    }

    public int getUserId(){
        return this.optInt("user_id");
    }

    public String getChatId(){
        return this.optString("chat_id");
    }

    public ChatMemberGetter setUserId(Object user_id){
        if(user_id instanceof User){
            this.put("user_id", ((User) user_id).getId());
        }else if(user_id instanceof Number){
            this.put("user_id", ((Number) user_id).intValue());
        }
        return this;
    }

    public ChatMemberGetter setChatId(Object chat_id){
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

    public ChatMember send(){
        return ChatMember.create(bot.updateResponse("getChatMember", this));
    }

}
