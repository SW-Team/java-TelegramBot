package milk.telegram.handler;

import milk.telegram.bot.TelegramBot;
import milk.telegram.update.Update;

import java.util.List;

public abstract class Handler{

    protected TelegramBot bot = null;

    public TelegramBot getBot(){
        return bot;
    }

    public void setBot(TelegramBot bot){
        this.bot = bot;
    }

    public abstract void update(List<Update> date);

}
