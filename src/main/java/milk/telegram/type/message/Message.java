package milk.telegram.type.message;

import milk.telegram.type.chat.Chat;
import milk.telegram.type.interfaces.Idable;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public class Message implements Idable<Integer>{

    private int date;
    private int message_id;

    private User from;
    private Chat chat;

    protected Message(JSONObject object){
        this.message_id = object.getInt("message_id");
        this.date = object.getInt("date");
        this.from = User.create(object.getJSONObject("from"));
        this.chat = Chat.create(object.getJSONObject("chat"));
    }

    public static Message create(JSONObject object){
        if(object == null){
            return null;
        }

        if(object.has("audio")){
            return new AudioMessage(object);
        }else if(object.has("contact")){
            return new ContactMessage(object);
        }else if(object.has("document")){
            return new DocumentMessage(object);
        }else if(object.has("location")){
            return new LocationMessage(object);
        }else if(object.has("photo")){
            return new PhotoMessage(object);
        }else if(object.has("sticker")){
            return new StickerMessage(object);
        }else if(object.has("forward_from_chat") || object.has("forward_from")){
            return new ForwardMessage(object);
        }else if(object.has("text")){
            return new TextMessage(object);
        }else if(object.has("venue")){
            return new VenueMessage(object);
        }else if(object.has("video")){
            return new VideoMessage(object);
        }else if(object.has("voice")){
            return new VoiceMessage(object);
        }
        return new Message(object);
    }

    public Integer getId(){
        return this.message_id;
    }

    public int getDate(){
        return this.date;
    }

    public User getFrom(){
        return this.from;
    }

    public Chat getChat(){
        return this.chat;
    }

}
