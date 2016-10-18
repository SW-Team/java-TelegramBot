package milk.telegram.method.setter;

import milk.telegram.type.user.User;
import milk.telegram.type.Usernamed;
import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.GameMessage;

import org.json.JSONObject;

public class GameScoreSetter extends Setter{

    protected String chat_id;
    protected String inline_id;

    protected long score;

    protected int user_id;
    protected long message_id;

    public GameScoreSetter(TelegramBot bot){
        super(bot);
    }

    public long getScore(){
        return score;
    }

    public int getUserId(){
        return user_id;
    }

    public String getChatId(){
        return chat_id;
    }

    public long getMessageId(){
        return message_id;
    }

    public String getInlineId(){
        return inline_id;
    }

    public GameScoreSetter setScore(Number score){
        this.score = score.longValue();
        return this;
    }

    public GameScoreSetter setUserId(Object user_id){
        if(user_id instanceof User){
            this.user_id = ((User) user_id).getId();
        }else if(user_id instanceof Number){
            this.user_id = ((Number) user_id).intValue();
        }
        return this;
    }

    public GameScoreSetter setChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String){
            this.chat_id = (String) chat_id;
        }else if(chat_id instanceof Number){
            this.chat_id = ((Number) chat_id).longValue() + "";
        }
        return this;
    }

    public GameScoreSetter setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.message_id = ((Message) message_id).getId();
            this.chat_id = ((Message) message_id).getChat().getId() + "";
        }else if(message_id instanceof Number){
            this.message_id = ((Number) message_id).longValue();
        }
        return this;
    }

    public GameScoreSetter setInlineId(String inline_id){
        this.inline_id = inline_id;
        return this;
    }

    public GameMessage send(){
        JSONObject object = new JSONObject();
        object.put("score", score);
        object.put("user_id", user_id);
        if(inline_id != null){
            object.put("inline_message_id", inline_id);
        }else{
            object.put("chat_id", chat_id);
            object.put("message_id", message_id);
        }

        return (GameMessage) Message.create(bot.updateResponse("setGameScore", object));
    }
}
