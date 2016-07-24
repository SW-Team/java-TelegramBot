package milk.telegram.event;

import cn.nukkit.event.Event;
import milk.telegram.bot.TelegramBot;
import milk.telegram.media.message.TextMessage;

public class TelegramCommandReceiveEvent extends Event{

    private final TextMessage message;

    private final TelegramBot telegramBot;

    private final String cmd;
    private final String[] args;

    public TelegramCommandReceiveEvent(TelegramBot telegramBot, TextMessage txt, String cmd, String[] args){
        this.telegramBot = telegramBot;
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

    public TelegramBot getTelegramBot(){
        return this.telegramBot;
    }

}
