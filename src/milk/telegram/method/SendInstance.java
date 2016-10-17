package milk.telegram.method;

import milk.telegram.bot.TelegramBot;

public class SendInstance{

    protected TelegramBot bot;

    public SendInstance(TelegramBot bot){
        this.setBot(bot);
    }

    public TelegramBot getBot(){
        return bot;
    }

    public SendInstance setBot(TelegramBot bot){
        this.bot = bot;
        return this;
    }

}
