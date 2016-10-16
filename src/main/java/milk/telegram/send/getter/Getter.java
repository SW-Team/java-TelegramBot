package milk.telegram.send.getter;

import milk.telegram.bot.TelegramBot;

public abstract class Getter{

    protected TelegramBot bot;

    public Getter(TelegramBot bot){
        this.setBot(bot);
    }

    public TelegramBot getBot(){
        return bot;
    }

    public Getter setBot(TelegramBot bot){
        this.bot = bot;
        return this;
    }

    public abstract Object send();

}
