package com.milk.telegram.media.message;

import com.milk.telegram.media.interfaces.Textable;
import com.milk.telegram.media.message.type.PhotoSize;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoMessage extends Message implements Textable{

    private String text;
    private ArrayList<PhotoSize> photo;

    public PhotoMessage(JSONObject object){
        super(object);

        this.text = object.optString("text", null);
        this.photo = new ArrayList<>();
        object.getJSONArray("photo").forEach(obj -> {
            if(obj instanceof JSONObject) this.photo.add(new PhotoSize((JSONObject) obj));
        });
    }

    public String getText(){
        return this.text;
    }

    public ArrayList<PhotoSize> getPhotoList(){
        return this.photo;
    }

}
