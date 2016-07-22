package milk.telegram.media.message;

import milk.telegram.media.message.type.Location;
import org.json.JSONObject;

public class LocationMessage extends Message{

    private Location location;

    public LocationMessage(JSONObject object){
        super(object);
        this.location = new Location(object.getJSONObject("location"));
    }

    public Location getLocation(){
        return this.location;
    }

}
