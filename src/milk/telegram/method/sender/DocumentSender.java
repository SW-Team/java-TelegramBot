package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Document;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.DocumentMessage;

import milk.telegram.type.reply.ReplyMarkup;

public class DocumentSender extends Sender{

    public DocumentSender(TelegramBot bot){
        super(bot);
    }

    public String getCaption(){
        return this.optString("caption");
    }

    public String getDocument(){
        return this.optString("document");
    }

    //Optional
    public DocumentSender setCaption(String caption){
        this.put("caption", caption);
        return this;
    }

    public DocumentSender setDocument(Object document){
        if(document instanceof Document){
            this.put("document", ((Document) document).getId());
        }else if(document instanceof String){
            this.put("document", document);
        }
        return this;
    }

    public DocumentSender setChatId(Object chat_id){
        return (DocumentSender) super.setChatId(chat_id);
    }

    //Optional
    public DocumentSender setMessageId(Object message_id){
        return (DocumentSender) super.setMessageId(message_id);
    }

    //Optional
    public DocumentSender setReplyMarkup(ReplyMarkup markup){
        return (DocumentSender) super.setReplyMarkup(markup);
    }

    //Optional
    public DocumentSender setDisableNotification(boolean value){
        return (DocumentSender) super.setDisableNotification(value);
    }

    public DocumentMessage send(){
        return (DocumentMessage) Message.create(bot.updateResponse("sendDocument", this));
    }

}
