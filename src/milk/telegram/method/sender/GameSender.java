package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.game.Game;
import milk.telegram.type.message.Message;
import milk.telegram.type.message.GameMessage;

import milk.telegram.type.reply.ReplyMarkup;

public class GameSender extends Sender{

    public GameSender(TelegramBot bot){
        super(bot);
    }

    public String getGame(){
        return this.optString("game_short_name");
    }

    public GameSender setGame(String game_short_name){
        this.put("game_short_name", game_short_name);
        return this;
    }

    public GameSender setChatId(Object chat_id){
        return (GameSender) super.setChatId(chat_id);
    }

    //Optional
    public GameSender setMessageId(Object message_id){
        return (GameSender) super.setMessageId(message_id);
    }

    //Optional
    public GameSender setReplyMarkup(ReplyMarkup markup){
        return (GameSender) super.setReplyMarkup(markup);
    }

    //Optional
    public GameSender setDisableNotification(boolean value){
        return (GameSender) super.setDisableNotification(value);
    }

    public GameMessage send(){
        return (GameMessage) Message.create(bot.updateResponse("sendGame", this));
    }

}
