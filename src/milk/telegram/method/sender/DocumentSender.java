package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Document;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.DocumentMessage;

import org.json.JSONObject;

public class DocumentSender extends MessageSender{

    protected String document;
    protected String caption = null;

    public DocumentSender(TelegramBot bot){
        super(bot);
    }

    public String getCaption(){
        return caption;
    }

    public String getDocument(){
        return document;
    }

    public DocumentSender setCaption(String caption){
        this.caption = caption;
        return this;
    }

    public DocumentSender setDocument(Object document){
        if(document instanceof Document){
            this.document = ((Document) document).getId();
        }else if(document instanceof String){
            this.document = (String) document;
        }
        return this;
    }

    @Override
    public DocumentSender setChatId(Object chat_id){
        return (DocumentSender) super.setChatId(chat_id);
    }

    @Override
    public DocumentSender setMessageId(Object message_id){
        return (DocumentSender) super.setMessageId(message_id);
    }

    @Override
    public DocumentSender setDisableNotification(boolean value){
        return (DocumentSender) super.setDisableNotification(value);
    }

    public DocumentMessage send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("document", document);
        object.put("disable_notification", disable_notification);

        if(caption != null) object.put("caption", caption);
        if(reply_markup != null) object.put("reply_markup", reply_markup);
        if(message_id != -1) object.put("reply_to_message_id", message_id);

        return (DocumentMessage) Message.create(bot.updateResponse("sendDocument", object));
    }

}
