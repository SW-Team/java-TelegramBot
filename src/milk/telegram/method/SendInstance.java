package milk.telegram.method;

import milk.telegram.bot.TelegramBot;
import org.json.JSONObject;

public abstract class SendInstance extends JSONObject{

    protected final TelegramBot bot;

    public SendInstance(TelegramBot bot){
        this.bot = bot;
    }

    public abstract Object send();

    public void asyncSend(){
        new Thread(this::send).start();
    }

}
