package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.chat.Chat;
import org.json.JSONObject;

public class ChatGetter extends Getter{

    protected String chat_id;

    public ChatGetter(TelegramBot bot){
        super(bot);
    }

    public String getChatId(){
        return chat_id;
    }

    public ChatGetter setChatId(Object chat_id){
        this.chat_id = (chat_id instanceof Number ? ((Number) chat_id).longValue() : chat_id) + "";
        return this;
    }

    public Chat send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        return Chat.create(bot.updateResponse("getChat", object));
    }

}
