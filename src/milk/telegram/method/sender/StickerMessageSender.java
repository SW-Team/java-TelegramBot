package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.photo.Sticker;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.StickerMessage;
import org.json.JSONObject;

public class StickerMessageSender extends MessageSender{

    protected String sticker;

    public StickerMessageSender(TelegramBot bot){
        super(bot);
    }

    public String getSticker(){
        return sticker;
    }

    public StickerMessageSender setSticker(Object sticker){
        if(sticker instanceof Sticker){
            this.sticker = ((Sticker) sticker).getId();
        }else if(!(sticker instanceof String)){
            return null;
        }
        return this;
    }

    public StickerMessage send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("sticker", sticker);
        object.put("disable_notification", disable_notification);
        if(message_id != -1) object.put("reply_to_message_id", message_id);

        return (StickerMessage) Message.create(bot.updateResponse("sendSticker", object));
    }

}
