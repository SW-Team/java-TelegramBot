package milk.telegram.type.message;

import milk.telegram.type.chat.Chat;
import milk.telegram.type.Identifier;
import milk.telegram.type.user.User;

import org.json.JSONObject;

public class Message implements Identifier<Integer>{

    private final int date;
    private final int message_id;

    private final User from;
    private final Chat chat;

    private final Message reply_message;

    private final User forward_from;
    private final Chat forward_from_chat;

    private final JSONObject object;

    protected Message(JSONObject object){
        this.object = object;

        this.date = object.getInt("date");
        this.message_id = object.getInt("message_id");
        this.chat = Chat.create(object.getJSONObject("chat"));

        this.from = User.create(object.optJSONObject("from"));
        this.reply_message = Message.create(object.optJSONObject("reply_to_message"));

        this.forward_from = User.create(object.optJSONObject("forward_from"));
        this.forward_from_chat = Chat.create(object.optJSONObject("forward_from_chat"));
    }

    public static Message create(JSONObject object){
        if(object == null){
            return null;
        }else if(object.has("result")){
            object = object.optJSONObject("result");
            if(object == null || object.length() < 1){
                return null;
            }
        }

        if(object.has("game")){
            return new GameMessage(object);
        }else if(object.has("audio")){
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

    public boolean isReplyMessage(){
        return this.reply_message != null;
    }

    public boolean isForwardMessage(){
        return this.forward_from != null || this.forward_from_chat != null;
    }

    public Message getReplyMessage(){
        return this.reply_message;
    }

    public User getForwardFrom(){
        return forward_from;
    }

    public Chat getForwardChat(){
        return forward_from_chat;
    }

    public String getName(){
        return "메시지";
    }

    public JSONObject toJSONObject(){
        return object;
    }

    @Override
    public String toString(){
        return this.getName();
    }

}
