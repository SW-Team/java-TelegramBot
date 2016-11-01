package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Document;
import milk.telegram.type.file.photo.PhotoSize;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.DocumentMessage;

import milk.telegram.type.reply.ReplyMarkup;

public class PhotoSender extends Sender{

    public PhotoSender(TelegramBot bot){
        super(bot);
    }

    public String getPhoto(){
        return this.optString("photo");
    }

    public String getCaption(){
        return this.optString("caption");
    }

    public PhotoSender setTitle(String title){
        this.put("title", title);
        return this;
    }

    public PhotoSender setPhoto(Object photo){
        if(photo instanceof PhotoSize){
            this.put("photo", ((PhotoSize) photo).getId());
        }else if(photo instanceof String){
            this.put("photo", photo);
        }
        return this;
    }

    //Optional
    public PhotoSender setCaption(String caption){
        this.put("caption", caption);
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
        return (DocumentMessage) Message.create(bot.updateResponse("sendPhoto", this));
    }

}
