package milk.telegram.method.editor;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class CaptionEditor extends Editor{

    protected String chat_id;
    protected String inline_id;

    protected long message_id = -1;

    protected String caption;

    protected JSONObject reply_markup = null;

    public CaptionEditor(TelegramBot bot){
        super(bot);
    }

    public String getCaption(){
        return caption;
    }

    public String getChatId(){
        return chat_id;
    }

    public long getMessageId(){
        return message_id;
    }

    public String getInlineId(){
        return inline_id;
    }

    public JSONObject getReplyMarkup(){
        return reply_markup;
    }

    public CaptionEditor setChatId(Object chat_id){
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

    public CaptionEditor setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.message_id = ((Message) message_id).getId();
            this.chat_id = ((Message) message_id).getChat().getId() + "";
        }else if(message_id instanceof Number){
            this.message_id = ((Number) message_id).longValue();
        }
        return this;
    }

    public CaptionEditor setReplyMarkup(ReplyMarkup reply_markup){
        this.reply_markup = reply_markup;
        return this;
    }

    public CaptionEditor setCaption(String caption){
        this.caption = caption;
        return this;
    }

    public CaptionEditor setInlineId(String inline_id){
        this.inline_id = inline_id;
        return this;
    }

    public Message send(){
        JSONObject object = new JSONObject();
        object.put("caption", caption);
        if(inline_id != null){
            object.put("inline_message_id", inline_id);
        }else{
            object.put("chat_id", chat_id);
            object.put("message_id", message_id);
        }

        return Message.create(bot.updateResponse("editMessageCaption", object));
    }

}
