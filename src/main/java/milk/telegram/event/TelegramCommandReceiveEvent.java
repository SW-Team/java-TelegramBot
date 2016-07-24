package milk.telegram.event;

import cn.nukkit.event.Event;
import milk.telegram.bot.Bot;
import milk.telegram.media.message.TextMessage;

public class TelegramCommandReceiveEvent extends Event{

    private final TextMessage message;

    private final Bot bot;

    private final String cmd;
    private final String[] args;

    public TelegramCommandReceiveEvent(Bot bot, TextMessage txt, String cmd, String[] args){
        this.bot = bot;
        this.message = txt;
        this.cmd = cmd;
        this.args = args;
    }

    public String getCommand(){
        return this.cmd;
    }

    public String[] getArgs(){
        return this.args;
    }

    public TextMessage getMessage(){
        return this.message;
    }

    public Bot getBot(){
        return this.bot;
    }

}
