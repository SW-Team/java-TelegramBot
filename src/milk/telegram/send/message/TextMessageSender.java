package milk.telegram.send.message;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.TextMessage;
import org.json.JSONObject;

public class TextMessageSender extends MessageSender{

    private String text;
    private String parse_mode = null;

    private boolean disable_web_page_preview = false;

    public TextMessageSender(TelegramBot bot){
        super(bot);
    }

    public String getText(){
        return text;
    }

    public boolean isDisableWebPagePreview(){
        return disable_web_page_preview;
    }

    public TextMessageSender setText(String text){
        this.text = text;
        return this;
    }

    public TextMessageSender setDisableWebPagePreview(boolean value){
        this.disable_web_page_preview = value;
        return this;
    }

    public TextMessage send(){
        JSONObject object = new JSONObject();
        object.put("text", text);
        object.put("chat_id", chat_id);
        object.put("disable_notification", disable_notification);
        object.put("disable_web_page_preview", disable_web_page_preview);

        if(parse_mode != null) object.put("parse_mode", parse_mode);
        if(message_id != -1) object.put("reply_to_message_id", message_id);

        return (TextMessage) Message.create(bot.updateResponse("sendMessage", object));
    }

}
