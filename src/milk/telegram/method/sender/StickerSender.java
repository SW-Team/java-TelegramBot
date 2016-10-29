package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.photo.Sticker;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.StickerMessage;
import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class StickerSender extends Sender{

    public StickerSender(TelegramBot bot){
        super(bot);
    }

    public String getSticker(){
        return this.optString("sticker");
    }

    public StickerSender setSticker(Object sticker){
        if(sticker instanceof Sticker){
            this.put("sticker", ((Sticker) sticker).getId());
        }else if(sticker instanceof String){
            this.put("sticker", sticker);
        }
        return this;
    }

    public StickerSender setChatId(Object chat_id){
        return (StickerSender) super.setChatId(chat_id);
    }

    //Optional
    public StickerSender setMessageId(Object message_id){
        return (StickerSender) super.setMessageId(message_id);
    }

    //Optional
    public StickerSender setReplyMarkup(ReplyMarkup markup){
        return (StickerSender) super.setReplyMarkup(markup);
    }

    //Optional
    public StickerSender setDisableNotification(boolean value){
        return (StickerSender) super.setDisableNotification(value);
    }

    public StickerMessage send(){
        return (StickerMessage) Message.create(bot.updateResponse("sendSticker", this));
    }

}
