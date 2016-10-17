package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import org.json.JSONObject;

public class ForwardMessageSender extends MessageSender{

    protected String from_chat_id;

    public ForwardMessageSender(TelegramBot bot){
        super(bot);
    }

    public String getFromChatId(){
        return from_chat_id;
    }

    public ForwardMessageSender setFromChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String){
            this.from_chat_id = (String) chat_id;
        }else if(chat_id instanceof Number){
            this.from_chat_id = ((Number) chat_id).longValue() + "";
        }
        return this;
    }

    @Override
    public ForwardMessageSender setChatId(Object chat_id){
        return (ForwardMessageSender) super.setChatId(chat_id);
    }

    @Override
    public ForwardMessageSender setMessageId(Object message_id){
        return (ForwardMessageSender) super.setMessageId(message_id);
    }

    @Override
    public ForwardMessageSender setDisableNotification(boolean value){
        return (ForwardMessageSender) super.setDisableNotification(value);
    }

    public Message send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("message_id", message_id);
        object.put("from_chat_id", from_chat_id);
        object.put("disable_notification", disable_notification);
        return Message.create(bot.updateResponse("forwardMessage", object));
    }

}
