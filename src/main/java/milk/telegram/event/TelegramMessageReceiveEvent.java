package milk.telegram.event;

import cn.nukkit.event.Event;
import milk.telegram.bot.Bot;
import milk.telegram.media.message.Message;

public class TelegramMessageReceiveEvent extends Event{

    private final Bot bot;
    private final Message message;

    public TelegramMessageReceiveEvent(Bot bot, Message msg){
        this.bot = bot;
        this.message = msg;
    }

    public Message getMessage(){
        return this.message;
    }

    public Bot getBot(){
        return this.bot;
    }

}