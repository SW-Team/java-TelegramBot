package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.photo.PhotoSize;
import milk.telegram.type.message.AudioMessage;
import milk.telegram.type.message.Message;
import milk.telegram.type.reply.ReplyMarkup;

public class AudioSender extends Sender{

    public AudioSender(TelegramBot bot){
        super(bot);
    }

    public String getTitle(){
        return this.optString("title");
    }

    public String getAudio(){
        return this.optString("audio");
    }

    public int getDuration(){
        return this.optInt("duration");
    }

    public String getCaption(){
        return this.optString("caption");
    }

    public String getPerformer(){
        return this.optString("performer");
    }

    public AudioSender setTitle(String title){
        this.put("title", title);
        return this;
    }

    //Optional
    public AudioSender setDuration(int duration){
        this.put("duration", duration);
        return this;
    }

    public AudioSender setAudio(Object audio){
        if(audio instanceof PhotoSize){
            this.put("audio", ((PhotoSize) audio).getId());
        }else if(audio instanceof String){
            this.put("audio", audio);
        }
        return this;
    }

    //Optional
    public AudioSender setCaption(String caption){
        this.put("caption", caption);
        return this;
    }

    //Optional
    public AudioSender setPerformer(String performer){
        this.put("performer", performer);
        return this;
    }

    public AudioSender setChatId(Object chat_id){
        return (AudioSender) super.setChatId(chat_id);
    }

    //Optional
    public AudioSender setMessageId(Object message_id){
        return (AudioSender) super.setMessageId(message_id);
    }

    //Optional
    public AudioSender setReplyMarkup(ReplyMarkup markup){
        return (AudioSender) super.setReplyMarkup(markup);
    }

    //Optional
    public AudioSender setDisableNotification(boolean value){
        return (AudioSender) super.setDisableNotification(value);
    }

    public AudioMessage send(){
        return (AudioMessage) Message.create(bot.updateResponse("sendAudio", this));
    }

}
