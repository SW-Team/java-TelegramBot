package milk.telegram.handler;

import milk.telegram.bot.TelegramBot;
import milk.telegram.media.Update;

import java.util.List;

public abstract class Handler{

    protected TelegramBot bot = null;

    public Handler(){}

    public Handler(TelegramBot bot){
        this.bot = bot;
    }

    public TelegramBot getBot(){
        return bot;
    }

    public void setBot(TelegramBot bot){
        this.bot = bot;
    }

    public boolean isActivate(){
        return this.bot != null;
    }

    public abstract void update(List<Update> date);

}
