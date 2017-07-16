package milk.telegram.method.update;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class CaptionEditor extends Editor{

    public CaptionEditor(TelegramBot bot){
        super(bot);
    }

    public String getCaption(){
        return this.optString("caption");
    }

    public String getChatId(){
        return this.optString("chat_id");
    }

    public long getMessageId(){
        return this.optLong("message_id");
    }

    public String getInlineId(){
        return this.optString("inline_id");
    }

    public JSONObject getReplyMarkup(){
        return this.optJSONObject("reply_markup");
    }

    public CaptionEditor setChatId(Object chat_id){
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

    public CaptionEditor setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.put("message_id", ((Message) message_id).getId());
            this.put("chat_id", ((Message) message_id).getChat().getId() + "");
        }else if(message_id instanceof Number){
            this.put("message_id", ((Number) message_id).longValue());
        }
        return this;
    }

    public CaptionEditor setReplyMarkup(ReplyMarkup reply_markup){
        this.put("reply_markup", reply_markup);
        return this;
    }

    public CaptionEditor setCaption(String caption){
        this.put("caption", caption);
        return this;
    }

    public CaptionEditor setInlineId(String inline_id){
        this.put("inline_id", inline_id);
        return this;
    }

    public Message send(){
        return Message.create(bot.updateResponse("editMessageCaption", this));
    }

}
