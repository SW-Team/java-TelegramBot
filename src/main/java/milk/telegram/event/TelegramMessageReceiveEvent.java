package milk.telegram.event;

import cn.nukkit.event.Event;
import milk.telegram.bot.TelegramBot;
import milk.telegram.media.message.Message;

public class TelegramMessageReceiveEvent extends Event{

    private final TelegramBot telegramBot;
    private final Message message;

    public TelegramMessageReceiveEvent(TelegramBot telegramBot, Message msg){
        this.telegramBot = telegramBot;
        this.message = msg;
    }

    public Message getMessage(){
        return this.message;
    }

    public TelegramBot getTelegramBot(){
        return this.telegramBot;
    }

}