package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.chat.Chat;

public class ChatGetter extends Getter{

    public ChatGetter(TelegramBot bot){
        super(bot);
    }

    public String getChatId(){
        return this.optString("chat_id");
    }

    public ChatGetter setChatId(Object chat_id){
        this.put("chat_id", (chat_id instanceof Number ? ((Number) chat_id).longValue() : chat_id) + "");
        return this;
    }

    public Chat send(){
        return Chat.create(bot.updateResponse("getChat", this));
    }

}
