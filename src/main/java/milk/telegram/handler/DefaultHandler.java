package milk.telegram.handler;

import cn.nukkit.Server;
import milk.telegram.bot.TelegramBot;
import milk.telegram.event.TelegramCommandReceiveEvent;
import milk.telegram.event.TelegramMessageReceiveEvent;
import milk.telegram.media.Update;
import milk.telegram.media.message.Message;
import milk.telegram.media.message.TextMessage;

import java.util.List;

public class DefaultHandler extends Handler{

    public DefaultHandler(TelegramBot bot){
        super(bot);
    }

    @Override
    public void update(List<Update> updateList){
        updateList.forEach(update -> {
            Message message = update.getMessage();
            if(message != null){
                if(message instanceof TextMessage){
                    TextMessage txt = (TextMessage) message;
                    if(txt.getText().startsWith("/")){
                        String text = txt.getText().substring(1);
                        String[] kk = text.split(" ");
                        String[] args = new String[kk.length - 1];
                        System.arraycopy(kk, 1, args, 0, args.length);

                        String cmd = kk[0];
                        if(cmd.contains("@")){
                            cmd = kk[0].substring(0, kk[0].indexOf("@"));
                        }
                        Server.getInstance().getPluginManager().callEvent(new TelegramCommandReceiveEvent(bot, txt, cmd, args));
                        return;
                    }
                }
                Server.getInstance().getPluginManager().callEvent(new TelegramMessageReceiveEvent(bot, message));
            }
        });
    }

}
