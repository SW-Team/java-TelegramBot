package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;

public class ForwardMessageSender extends Sender{

    public ForwardMessageSender(TelegramBot bot){
        super(bot);
    }

    public String getFromChatId(){
        return this.optString("from_chat_id");
    }

    public ForwardMessageSender setFromChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String){
            this.put("from_chat_id",  chat_id);
        }else if(chat_id instanceof Number){
            this.put("from_chat_id", ((Number) chat_id).longValue() + "");
        }
        return this;
    }

    public ForwardMessageSender setChatId(Object chat_id){
        return (ForwardMessageSender) super.setChatId(chat_id);
    }

    public ForwardMessageSender setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.put("message_id", ((Message) message_id).getId());
            this.put("from_chat_id", ((Message) message_id).getChat().getId() + "");
        }else if(message_id instanceof Number){
            this.put("message_id", ((Number) message_id).longValue());
        }
        return this;
    }

    //Optional
    public ForwardMessageSender setDisableNotification(boolean value){
        return (ForwardMessageSender) super.setDisableNotification(value);
    }

    public Message send(){
        return Message.create(bot.updateResponse("forwardMessage", this));
    }

}
