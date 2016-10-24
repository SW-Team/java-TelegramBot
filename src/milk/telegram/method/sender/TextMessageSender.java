package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.TextMessage;
import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class TextMessageSender extends Sender{

    protected String text;
    protected String parse_mode = null;

    private boolean disable_web_page_preview = false;

    public TextMessageSender(TelegramBot bot){
        super(bot);
    }

    public String getText(){
        return text;
    }

    public String getParseMode(){
        return parse_mode;
    }

    public boolean isDisableWebPagePreview(){
        return disable_web_page_preview;
    }

    public TextMessageSender setText(String text){
        this.text = text;
        return this;
    }

    public TextMessageSender setParseMode(String parse_mode){
        this.parse_mode = parse_mode;
        return this;
    }

    public TextMessageSender setDisableWebPagePreview(boolean value){
        this.disable_web_page_preview = value;
        return this;
    }

    @Override
    public TextMessageSender setChatId(Object chat_id){
        return (TextMessageSender) super.setChatId(chat_id);
    }

    @Override
    public TextMessageSender setMessageId(Object message_id){
        return (TextMessageSender) super.setMessageId(message_id);
    }

    @Override
    public TextMessageSender setReplyMarkup(ReplyMarkup reply_markup){
        return (TextMessageSender) super.setReplyMarkup(reply_markup);
    }

    @Override
    public TextMessageSender setDisableNotification(boolean value){
        return (TextMessageSender) super.setDisableNotification(value);
    }

    public TextMessage send(){
        JSONObject object = new JSONObject();
        object.put("text", text);
        object.put("chat_id", chat_id);
        object.put("disable_notification", disable_notification);
        object.put("disable_web_page_preview", disable_web_page_preview);

        if(parse_mode != null) object.put("parse_mode", parse_mode);
        if(reply_markup != null) object.put("reply_markup", reply_markup);
        if(message_id != -1) object.put("reply_to_message_id", message_id);

        return (TextMessage) Message.create(bot.updateResponse("sendMessage", object));
    }

}
