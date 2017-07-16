package milk.telegram.method.update;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.Usernamed;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.TextMessage;

import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class TextEditor extends Editor{

    public TextEditor(TelegramBot bot){
        super(bot);
    }

    public String getText(){
        return this.optString("text");
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

    public String getParseMode(){
        return this.optString("parse_mode");
    }

    public JSONObject getReplyMarkup(){
        return this.optJSONObject("reply_markup");
    }

    public boolean isDisableWebPagePreview(){
        return this.optBoolean("disable_web_page_preview");
    }

    public TextEditor setChatId(Object chat_id){
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

    public TextEditor setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.put("message_id", ((Message) message_id).getId());
            this.put("chat_id", ((Message) message_id).getChat().getId() + "");
        }else if(message_id instanceof Number){
            this.put("message_id", ((Number) message_id).longValue());
        }
        return this;
    }

    public TextEditor setReplyMarkup(ReplyMarkup reply_markup){
        this.put("reply_markup", reply_markup);
        return this;
    }

    public TextEditor setText(String text){
        this.put("text", text);
        return this;
    }

    public TextEditor setInlineId(String inline_id){
        this.put("inline_id", inline_id);
        return this;
    }

    public TextEditor setParseMode(String parse_mode){
        this.put("parse_mode", parse_mode);
        return this;
    }

    public TextEditor setDisableWebPagePreview(boolean value){
        this.put("disable_web_page_preview", value);
        return this;
    }

    public TextMessage send(){
        return (TextMessage) Message.create(bot.updateResponse("editMessageText", this));
    }

}
