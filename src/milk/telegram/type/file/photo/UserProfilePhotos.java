package milk.telegram.type.file.photo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserProfilePhotos{

    private final int total_count;
    private final ArrayList<ArrayList<PhotoSize>> photos = new ArrayList<>();

    private UserProfilePhotos(JSONObject object){
        this.total_count = object.getInt("total_count");
        object.getJSONArray("photos").forEach((photo) -> {
            ArrayList<PhotoSize> size = new ArrayList<>();
            ((JSONArray) photo).forEach((real -> size.add(PhotoSize.create((JSONObject) real))));
            photos.add(size);
        });
    }

    public static UserProfilePhotos create(JSONObject object){
        if(object == null){
            return null;
        }else if(object.has("result")){
            object = object.optJSONObject("result");
            if(object.length() < 1){
                return null;
            }
        }
        return new UserProfilePhotos(object);
    }

    public ArrayList<ArrayList<PhotoSize>> getPhotos(){
        return photos;
    }

    public int getTotalCount(){
        return total_count;
    }
}
