package milk.telegram.method.message;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Location;
import milk.telegram.type.file.Venue;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.VenueMessage;

import org.json.JSONObject;

public class VenueSender extends MessageSender{

    private double latitude;
    private double longitude;

    private String title;
    private String address;
    private String foursquare_id = null;

    public VenueSender(TelegramBot bot){
        super(bot);
    }

    public String getTitle(){
        return title;
    }

    public String getAddress(){
        return address;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public String getFoursquareId(){
        return foursquare_id;
    }

    public VenueSender setTitle(String title){
        this.title = title;
        return this;
    }

    public VenueSender setAddress(String address){
        this.address = address;
        return this;
    }

    public VenueSender setLatitude(double latitude){
        this.latitude = latitude;
        return this;
    }

    public VenueSender setLongitude(double longitude){
        this.longitude = longitude;
        return this;
    }

    public VenueSender setFoursquareId(String foursquare_id){
        this.foursquare_id = foursquare_id;
        return this;
    }

    public VenueSender setVenue(Venue venue){
        Location location = venue.getLocation();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        this.title = venue.getTitle();
        this.address = venue.getAddress();
        this.foursquare_id = venue.getFoursquareId();
        return this;
    }

    public VenueMessage send(){
        JSONObject object = new JSONObject();
        object.put("title", title);
        object.put("address", address);
        object.put("chat_id", chat_id);
        object.put("latitude", latitude);
        object.put("longitude", longitude);
        object.put("disable_notification", disable_notification);

        if(message_id != -1) object.put("reply_to_message_id", message_id);
        if(reply_markup != null) object.put("reply_markup", reply_markup);

        return (VenueMessage) Message.create(bot.updateResponse("sendMessage", object));
    }

}
