package milk.telegram.method.replier;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;

public abstract class Replier extends SendInstance{

    public Replier(TelegramBot bot){
        super(bot);
    }

}
