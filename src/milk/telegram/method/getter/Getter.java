package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;

public abstract class Getter extends SendInstance{

    public Getter(TelegramBot bot){
        super(bot);
    }

}
