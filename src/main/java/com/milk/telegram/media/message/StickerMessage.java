package com.milk.telegram.media.message;

import com.milk.telegram.media.message.type.Sticker;
import org.json.JSONObject;

public class StickerMessage extends Message{

    private Sticker sticker;

    public StickerMessage(JSONObject object){
        super(object);

        this.sticker = new Sticker(object.getJSONObject("sticker"));
    }

    public Sticker getSticker(){
        return this.sticker;
    }

}
