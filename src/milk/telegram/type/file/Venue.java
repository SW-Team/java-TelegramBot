package milk.telegram.type.file;

import org.json.JSONObject;

public class Venue{

    private final Location location;

    private final String title;
    private final String address;

    private final String foursquare_id;

    private Venue(JSONObject object){
        this.location = Location.create(object.getJSONObject("location"));

        this.title = object.getString("title");
        this.address = object.getString("address");

        this.foursquare_id = object.getString("foursquare_id");
    }

    public static Venue create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Venue(object);
    }

    public Location getLocation(){
        return location;
    }

    public String getTitle(){
        return title;
    }

    public String getAddress(){
        return address;
    }

    public String getFoursquareId(){
        return foursquare_id;
    }

}
