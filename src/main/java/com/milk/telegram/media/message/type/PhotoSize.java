package com.milk.telegram.media.message.type;

import org.json.JSONObject;

public class PhotoSize{

    private final int width;
    private final int height;

    public PhotoSize(JSONObject object){
        this.width  = object.getInt("width");
        this.height = object.getInt("height");
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
}
