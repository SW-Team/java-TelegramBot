package milk.telegram.event;

import cn.nukkit.event.Event;
import milk.telegram.media.message.TextMessage;

public class TelegramCommandReceiveEvent extends Event{

    private final TextMessage message;

    private final String cmd;
    private final String[] args;

    public TelegramCommandReceiveEvent(TextMessage txt, String cmd, String[] args){
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

}
