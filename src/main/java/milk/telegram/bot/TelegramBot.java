package milk.telegram.bot;

import cn.nukkit.Server;
import milk.telegram.event.TelegramCommandReceiveEvent;
import milk.telegram.event.TelegramMessageReceiveEvent;
import milk.telegram.media.chat.PrivateChat;
import milk.telegram.media.interfaces.IntegerId;
import milk.telegram.media.interfaces.Usernamed;
import milk.telegram.media.message.Message;
import milk.telegram.media.message.TextMessage;
import milk.telegram.media.user.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class TelegramBot{

    private final String token;

    private int lastId = 0;
    private boolean running = true;

    private final Thread updater;

    public TelegramBot(String token){
        this.token = token;
        TelegramBot that  = this;
        this.updater = new Thread(() -> {
            while(true){
                try{
                    JSONObject k = new JSONObject();
                    if(this.lastId > 0){
                        k.put("offset", this.lastId + 1);
                    }
                    JSONObject update = this.updateResponse("getUpdates", k);
                    if(update == null){
                        continue;
                    }

                    JSONArray array = update.getJSONArray("result");
                    array.forEach(val -> {
                        if(!(val instanceof JSONObject)){
                            return;
                        }

                        JSONObject object = (JSONObject) val;
                        if(!object.isNull("update_id")){
                            this.lastId = object.getInt("update_id");
                        }

                        Message message = Message.create(object.getJSONObject("message"));
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
                                    Server.getInstance().getPluginManager().callEvent(new TelegramCommandReceiveEvent(that, txt, cmd, args));
                                    return;
                                }
                            }
                            Server.getInstance().getPluginManager().callEvent(new TelegramMessageReceiveEvent(that, message));
                        }
                    });

                    if(!this.running) break;
                }catch(Exception e){}
            }
        });
    }

    public void run(){
        try{
            this.running = true;
            this.updater.start();
        }catch(IllegalThreadStateException e){}
    }

    public void stop(){
        this.running = false;
    }

    public boolean isRunning(){
        return this.running;
    }

    private JSONObject updateResponse(String key, JSONObject object){
        try{
            URL url = new URL(String.format("https://api.telegram.org/bot%s/%s", this.token, key));
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(500);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            if(object.length() > 0){
                connection.setDoOutput(true);
                try(OutputStream stream = connection.getOutputStream()){
                    stream.write(object.toString().getBytes(StandardCharsets.UTF_8));
                }
            }

            return new JSONObject(new JSONTokener(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)));
        }catch(Exception e){
            Server.getInstance().getLogger().debug("Class: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
            return null;
        }
    }

    public boolean sendMessage(String message, Object obj1){
        return sendMessage(message, obj1, -1);
    }

    public boolean sendMessage(String message, Object obj1, Object obj2){
        Object chat_id = obj1;
        if(obj1 instanceof Usernamed && !(obj1 instanceof User || obj1 instanceof PrivateChat)){
            chat_id = "@" + ((Usernamed) obj1).getUsername();
        }else if(obj1 instanceof IntegerId){
            chat_id = ((IntegerId) obj1).getId();
        }else if(!(obj1 instanceof Integer) && !(obj1 instanceof String)){
            return false;
        }

        Object msg_id = obj2;
        if(obj2 instanceof Message){
            msg_id = ((Message) obj2).getId();
        }else if(!(obj2 instanceof Integer)){
            return false;
        }

        String newtx = "";
        if(message.length() > 4096){
            newtx = message.substring(4096);
            message = message.substring(0, 4096);
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("text", message);
        if(((int) msg_id) != -1){
            object.put("reply_to_message_id", msg_id);
        }

        boolean val = updateResponse("sendMessage", object) != null;
        if(!newtx.equals("")){
            val = sendMessage(newtx, chat_id, msg_id);
        }
        return val;
    }

    public boolean sendSticker(String sticker, Object obj1){
        return sendSticker(sticker, obj1, -1);
    }

    public boolean sendSticker(String sticker, Object obj1, Object obj2){
        Object chat_id = obj1;
        if(obj1 instanceof Usernamed && !(obj1 instanceof User || obj1 instanceof PrivateChat)){
            chat_id = "@" + ((Usernamed) obj1).getUsername();
        }else if(obj1 instanceof IntegerId){
            chat_id = ((IntegerId) obj1).getId();
        }else if(!(obj1 instanceof Integer) && !(obj1 instanceof String)){
            return false;
        }

        Object msg_id = obj2;
        if(obj2 instanceof Message){
            msg_id = ((Message) obj2).getId();
        }else if(!(obj2 instanceof Integer)){
            return false;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("sticker", sticker);
        if(((int) msg_id) != -1){
            object.put("reply_to_message_id", msg_id);
        }

        return updateResponse("sendSticker", object) != null;
    }

}