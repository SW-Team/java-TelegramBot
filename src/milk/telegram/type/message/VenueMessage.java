package milk.telegram.type.message;

import milk.telegram.type.file.Venue;
import org.json.JSONObject;

public class VenueMessage extends Message{

    private Venue venue;

    public VenueMessage(JSONObject object){
        super(object);
        this.venue = Venue.create(object.getJSONObject("venue"));
    }

    public Venue getVenue(){
        return this.venue;
    }

    public String getName(){
        return "Venue";
    }

}
