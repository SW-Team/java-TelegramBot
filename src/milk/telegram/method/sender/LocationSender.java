package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Location;
import milk.telegram.type.message.Message;
import milk.telegram.type.reply.ReplyMarkup;
import milk.telegram.type.message.LocationMessage;

public class LocationSender extends Sender{

    public LocationSender(TelegramBot bot){
        super(bot);
    }

    public double getLatitude(){
        return this.optDouble("latitude");
    }

    public double getLongitude(){
        return this.optDouble("longitude");
    }

    public LocationSender setLatitude(double latitude){
        this.put("latitude", latitude);
        return this;
    }

    public LocationSender setLongitude(double longitude){
        this.put("longitude", longitude);
        return this;
    }

    //Optional
    public LocationSender setLocation(Location location){
        this.put("latitude", location.getLatitude());
        this.put("longitude", location.getLongitude());
        return this;
    }

    public LocationSender setChatId(Object chat_id){
        return (LocationSender) super.setChatId(chat_id);
    }

    //Optional
    public LocationSender setMessageId(Object message_id){
        return (LocationSender) super.setMessageId(message_id);
    }

    //Optional
    public LocationSender setReplyMarkup(ReplyMarkup markup){
        return (LocationSender) super.setReplyMarkup(markup);
    }

    //Optional
    public LocationSender setDisableNotification(boolean value){
        return (LocationSender) super.setDisableNotification(value);
    }

    public LocationMessage send(){
        return (LocationMessage) Message.create(bot.updateResponse("sendLocation", this));
    }

}
