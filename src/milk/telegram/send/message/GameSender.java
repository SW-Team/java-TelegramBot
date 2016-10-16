package milk.telegram.send.message;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.message.GameMessage;
import milk.telegram.type.message.Message;
import org.json.JSONObject;

public class GameSender extends MessageSender{

    private String game_short_name;

    public GameSender(TelegramBot bot){
        super(bot);
    }

    public String getGame(){
        return game_short_name;
    }

    public GameSender setGame(String game_short_name){
        this.game_short_name = game_short_name;
        return this;
    }

    public GameMessage send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("game_short_name", game_short_name);
        object.put("disable_notification", disable_notification);

        if(message_id != -1) object.put("reply_to_message_id", message_id);
        if(reply_markup != null) object.put("reply_markup", reply_markup);

        return (GameMessage) Message.create(bot.updateResponse("sendGame", object));
    }

}
