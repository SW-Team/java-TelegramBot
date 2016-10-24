package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import milk.telegram.type.reply.ReplyMarkup;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public abstract class Sender extends SendInstance{

    protected String chat_id;

    protected int user_id;
    protected long message_id = -1;

    protected JSONObject reply_markup = null;

    protected boolean disable_notification = false;

    public Sender(TelegramBot bot){
        super(bot);
    }

    public int getUserId(){
        return user_id;
    }

    public String getChatId(){
        return chat_id;
    }

    public long getMessageId(){
        return message_id;
    }

    public JSONObject getReplyMarkup(){
        return reply_markup;
    }

    public boolean isDisableNotification(){
        return disable_notification;
    }

    public Sender setUserId(Object user_id){
        if(user_id instanceof User){
            this.user_id = ((User) user_id).getId();
        }else if(user_id instanceof Number){
            this.user_id = ((Number) user_id).intValue();
        }
        return this;
    }

    public Sender setChatId(Object chat_id){
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

    public Sender setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.message_id = ((Message) message_id).getId();
            this.chat_id = ((Message) message_id).getChat().getId() + "";
        }else if(message_id instanceof Number){
            this.message_id = ((Number) message_id).longValue();
        }
        return this;
    }

    public Sender setReplyMarkup(ReplyMarkup reply_markup){
        this.reply_markup = reply_markup;
        return this;
    }

    public Sender setDisableNotification(boolean value){
        this.disable_notification = value;
        return this;
    }

}
