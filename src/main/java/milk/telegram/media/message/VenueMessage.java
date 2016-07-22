package milk.telegram.media.message;

import milk.telegram.media.message.type.Venue;
import org.json.JSONObject;

public class VenueMessage extends Message{

    private Venue venue;

    public VenueMessage(JSONObject object){
        super(object);
        this.venue = new Venue(object.getJSONObject("venue"));
    }

    public Venue getVenue(){
        return this.venue;
    }

}
