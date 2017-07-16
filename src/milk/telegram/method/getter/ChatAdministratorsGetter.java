package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.user.ChatMember;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatAdministratorsGetter extends Getter{

    public ChatAdministratorsGetter(TelegramBot bot){
        super(bot);
    }

    public String getChatId(){
        return this.optString("chat_id");
    }

    public ChatAdministratorsGetter setChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String){
            this.put("chat_id" , chat_id);
        }else if(chat_id instanceof Number){
            this.put("chat_id", ((Number) chat_id).longValue() + "");
        }
        return this;
    }

    public ArrayList<ChatMember> send(){
        JSONObject ob = bot.updateResponse("getChatAdministrators", this);
        if(ob != null && ob.has("result")){
            JSONArray ar = ob.getJSONArray("result");
            ArrayList<ChatMember> array = new ArrayList<>();
            for(Object obj : ar) array.add(ChatMember.create((JSONObject) obj));
            return array;
        }
        return null;
    }

}
