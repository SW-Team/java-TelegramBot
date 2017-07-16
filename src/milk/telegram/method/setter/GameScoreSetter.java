package milk.telegram.method.setter;

import milk.telegram.method.SendInstance;
import milk.telegram.method.update.Editor;
import milk.telegram.type.user.User;
import milk.telegram.type.Usernamed;
import milk.telegram.bot.TelegramBot;
import milk.telegram.type.Identifier;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.GameMessage;

import org.json.JSONObject;

public class GameScoreSetter extends Editor{

    public GameScoreSetter(TelegramBot bot){
        super(bot);
    }

    public long getScore(){
        return optLong("score");
    }

    public int getUserId(){
        return optInt("user_id");
    }

    public String getChatId(){
        return optString("chat_id");
    }

    public long getMessageId(){
        return optLong("message_id");
    }

    public String getInlineId(){
        return optString("inline_id");
    }

    public GameScoreSetter setScore(Number score){
        this.put("score", score.longValue());
        return this;
    }

    public GameScoreSetter setUserId(Object user_id){
        if(user_id instanceof User){
            this.put("user_id", ((User) user_id).getId());
        }else if(user_id instanceof Number){
            this.put("user_id", ((Number) user_id).intValue());
        }
        return this;
    }

    public GameScoreSetter setChatId(Object chat_id){
        if(chat_id instanceof Identifier){
            chat_id = chat_id instanceof Channel ? "@" + ((Usernamed) chat_id).getUsername() : ((Identifier) chat_id).getId();
        }

        if(chat_id instanceof String || chat_id instanceof Number){
            this.put("chat_id", chat_id);
        }
        return this;
    }

    public GameScoreSetter setMessageId(Object message_id){
        if(message_id instanceof Message){
            this.put("message_id", ((Message) message_id).getId());
            this.put("chat_id", ((Message) message_id).getChat().getId());
        }else if(message_id instanceof Number){
            this.put("message_id", message_id);
        }
        return this;
    }

    public GameScoreSetter setInlineId(String inline_id){
        this.put("inline_id", inline_id);
        return this;
    }

    public GameMessage send(){
        return (GameMessage) Message.create(bot.updateResponse("setGameScore", this));
    }
}
