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

    private final User forward_from;
    private final Chat forward_from_chat;

    protected Message(JSONObject object){
        this.message_id = object.getInt("message_id");
        this.date = object.getInt("date");
        this.from = User.create(object.getJSONObject("from"));
        this.chat = Chat.create(object.getJSONObject("chat"));

        this.forward_from = User.create(object.optJSONObject("forward_from"));
        this.forward_from_chat = Chat.create(object.optJSONObject("forward_from_chat"));
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

    public boolean isForwardMessage(){
        return this.forward_from != null || this.forward_from_chat != null;
    }

    public User getForwardFrom(){
        return forward_from;
    }

    public Chat getForwardChat(){
        return forward_from_chat;
    }

}
