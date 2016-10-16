package milk.telegram.type.game;

import milk.telegram.type.Titled;
import milk.telegram.type.file.photo.PhotoSize;
import milk.telegram.type.message.MessageEntity;
import milk.telegram.type.Textable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Game implements Titled, Textable{

    private String text;
    private String title;
    private String description;

    private Animation animation;

    private ArrayList<PhotoSize> photo = new ArrayList<>();
    private ArrayList<MessageEntity> text_entities = new ArrayList<>();

    private Game(JSONObject object){
        this.title = object.getString("title");
        this.description = object.getString("description");
        object.getJSONArray("photo").forEach(obj -> this.photo.add(PhotoSize.create((JSONObject) obj)));


        this.text = object.optString("text", null);
        JSONArray array = object.optJSONArray("text_entities");
        this.animation = Animation.create(object.optJSONObject("animation"));
        if(array != null) array.forEach(obj -> this.text_entities.add(MessageEntity.create((JSONObject) obj)));
    }

    public static Game create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Game(object);
    }

    @Override
    public String getText(){
        return text;
    }

    @Override
    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Animation getAnimation(){
        return animation;
    }
}
