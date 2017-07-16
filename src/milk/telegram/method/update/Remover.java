package milk.telegram.method.update;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;

public abstract class Remover extends SendInstance{

    public Remover(TelegramBot bot){
        super(bot);
    }

}
