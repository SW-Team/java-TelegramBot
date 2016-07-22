package milk.telegram.media.message;

import milk.telegram.media.chat.Chat;
import milk.telegram.media.interfaces.IntegerId;
import milk.telegram.media.user.User;
import org.json.JSONObject;

public class Message implements IntegerId{

    private int date;
    private int message_id;

    private User from;
    private Chat chat;

    public Message(JSONObject object){
        this.message_id = object.getInt("message_id");
        this.date = object.getInt("date");
        this.from = new User(object.getJSONObject("from"));
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

    public int getId(){
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
