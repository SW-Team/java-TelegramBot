package milk.telegram.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Venue;

public class VenueSender extends MessageSender{

    public VenueSender(TelegramBot bot){
        super(bot);
    }

    public Venue send(){
        return null;
    }

}
