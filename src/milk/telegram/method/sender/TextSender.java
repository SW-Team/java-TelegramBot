package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.TextMessage;
import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class TextSender extends Sender{

    public TextSender(TelegramBot bot){
        super(bot);
    }

    public String getText(){
        return this.optString("text");
    }

    public String getParseMode(){
        return this.optString("parse_mode");
    }

    public boolean isDisableWebPagePreview(){
        return this.optBoolean("disable_web_page_preview");
    }

    public TextSender setText(String text){
        this.put("text", text);
        return this;
    }

    //Optional
    public TextSender setParseMode(String parse_mode){
        this.put("parse_mode", parse_mode);
        return this;
    }

    //Optional
    public TextSender setDisableWebPagePreview(boolean value){
        this.put("disable_web_page_preview", value);
        return this;
    }

    public TextSender setChatId(Object chat_id){
        return (TextSender) super.setChatId(chat_id);
    }

    //Optional
    public TextSender setMessageId(Object message_id){
        return (TextSender) super.setMessageId(message_id);
    }

    //Optional
    public TextSender setReplyMarkup(ReplyMarkup markup){
        return (TextSender) super.setReplyMarkup(markup);
    }

    //Optional
    public TextSender setDisableNotification(boolean value){
        return (TextSender) super.setDisableNotification(value);
    }

    public TextMessage send(){
        return (TextMessage) Message.create(bot.updateResponse("sendMessage", this));
    }

}
