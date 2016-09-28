package milk.telegram.type.message;

import milk.telegram.type.file.Location;
import org.json.JSONObject;

public class LocationMessage extends Message{

    private Location location;

    public LocationMessage(JSONObject object){
        super(object);
        this.location = Location.create(object.getJSONObject("location"));
    }

    public Location getLocation(){
        return this.location;
    }

    public String getName(){
        return "위치정보";
    }

}
