package milk.telegram.method.setter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;

public abstract class Setter extends SendInstance{

    public Setter(TelegramBot bot){
        super(bot);
    }

}
