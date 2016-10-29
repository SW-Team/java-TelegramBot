package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;

import org.json.JSONObject;

public class ChatActionSender extends Sender{

    public ChatActionSender(TelegramBot bot){
        super(bot);
    }

    public String getAction(){
        return this.optString("action");
    }

    public ChatActionSender setAction(String action){
        this.put("action", action);
        return this;
    }

    public ChatActionSender setChatId(Object chat_id){
        return (ChatActionSender) super.setChatId(chat_id);
    }

    public Object send(){
        bot.updateResponse("sendChatAction", this);
        return null;
    }

}
