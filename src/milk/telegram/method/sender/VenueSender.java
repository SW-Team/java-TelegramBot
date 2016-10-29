package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Location;
import milk.telegram.type.file.Venue;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.VenueMessage;

import milk.telegram.type.reply.ReplyMarkup;
import org.json.JSONObject;

public class VenueSender extends Sender{

    public VenueSender(TelegramBot bot){
        super(bot);
    }

    public String getTitle(){
        return this.optString("title");
    }

    public String getAddress(){
        return this.optString("address");
    }

    public double getLatitude(){
        return this.optDouble("latitude");
    }

    public double getLongitude(){
        return this.optDouble("longitude");
    }

    public String getFoursquareId(){
        return this.optString("foursquare_id");
    }

    public VenueSender setTitle(String title){
        this.put("title", title);
        return this;
    }

    public VenueSender setAddress(String address){
        this.put("address", address);
        return this;
    }

    public VenueSender setLatitude(double latitude){
        this.put("latitude", latitude);
        return this;
    }

    public VenueSender setLongitude(double longitude){
        this.put("longitude", longitude);
        return this;
    }

    //Optional
    public VenueSender setFoursquareId(String foursquare_id){
        this.put("foursquare_id", foursquare_id);
        return this;
    }

    //Optional
    public VenueSender setVenue(Venue venue){
        Location location = venue.getLocation();
        this.put("latitude", location.getLatitude());
        this.put("longitude", location.getLongitude());

        this.put("title", venue.getTitle());
        this.put("address", venue.getAddress());
        this.put("foursquare_id", venue.getFoursquareId());
        return this;
    }

    public VenueSender setChatId(Object chat_id){
        return (VenueSender) super.setChatId(chat_id);
    }

    //Optional
    public VenueSender setMessageId(Object message_id){
        return (VenueSender) super.setMessageId(message_id);
    }

    //Optional
    public VenueSender setReplyMarkup(ReplyMarkup markup){
        return (VenueSender) super.setReplyMarkup(markup);
    }

    //Optional
    public VenueSender setDisableNotification(boolean value){
        return (VenueSender) super.setDisableNotification(value);
    }

    public VenueMessage send(){
        return (VenueMessage) Message.create(bot.updateResponse("sendVenue", this));
    }

}
