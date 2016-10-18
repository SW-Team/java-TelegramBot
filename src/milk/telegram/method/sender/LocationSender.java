package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Location;
import milk.telegram.type.message.LocationMessage;
import milk.telegram.type.message.Message;

import org.json.JSONObject;

public class LocationSender extends Sender{

    protected double latitude;
    protected double longitude;

    public LocationSender(TelegramBot bot){
        super(bot);
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public LocationSender setLatitude(double latitude){
        this.latitude = latitude;
        return this;
    }

    public LocationSender setLongitude(double longitude){
        this.longitude = longitude;
        return this;
    }

    public LocationSender setLocation(Location location){
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        return this;
    }

    @Override
    public LocationSender setChatId(Object chat_id){
        return (LocationSender) super.setChatId(chat_id);
    }

    @Override
    public LocationSender setMessageId(Object message_id){
        return (LocationSender) super.setMessageId(message_id);
    }

    @Override
    public LocationSender setDisableNotification(boolean value){
        return (LocationSender) super.setDisableNotification(value);
    }

    public LocationMessage send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("latitude", latitude);
        object.put("longitude", longitude);
        object.put("disable_notification", disable_notification);

        if(reply_markup != null) object.put("reply_markup", reply_markup);
        if(message_id != -1) object.put("reply_to_message_id", message_id);

        return (LocationMessage) Message.create(bot.updateResponse("sendLocation", object));
    }

}
