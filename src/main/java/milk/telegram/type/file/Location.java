package milk.telegram.type.file;

import org.json.JSONObject;

public class Location{

    private final double latitude;
    private final double longitude;

    private Location(JSONObject object){
        this.latitude = object.getDouble("latitude");
        this.longitude = object.getDouble("longitude");
    }

    public static Location create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Location(object);
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

}
