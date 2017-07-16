package milk.telegram.method.update;

import milk.telegram.bot.TelegramBot;

public class MessageRemover extends Remover{

    public MessageRemover(TelegramBot bot){
        super(bot);
    }

    public Object send(){
        return null;
    }
}
