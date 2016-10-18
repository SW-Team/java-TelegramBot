package milk.telegram.method.editor;

import milk.telegram.bot.TelegramBot;
import milk.telegram.method.SendInstance;

public abstract class Editor extends SendInstance{

    public Editor(TelegramBot bot){
        super(bot);
    }

}
