package milk.telegram.type.message;

import milk.telegram.type.file.photo.Sticker;
import org.json.JSONObject;

public class StickerMessage extends Message{

    private Sticker sticker;

    public StickerMessage(JSONObject object){
        super(object);

        this.sticker = Sticker.create(object.getJSONObject("sticker"));
    }

    public Sticker getSticker(){
        return this.sticker;
    }

    public String getName(){
        return "스티커";
    }

}
