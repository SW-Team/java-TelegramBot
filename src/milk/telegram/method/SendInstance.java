package milk.telegram.method;

import milk.telegram.bot.TelegramBot;

public abstract class SendInstance{

    protected TelegramBot bot;

    public SendInstance(TelegramBot bot){
        this.setBot(bot);
    }

    public TelegramBot getBot(){
        return bot;
    }

    public void setBot(TelegramBot bot){
        this.bot = bot;
    }

    public abstract Object send();

    public void asyncSend(){
        new Thread(this::send).start();
    }

}
