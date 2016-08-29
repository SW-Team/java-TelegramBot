package milk.telegram.bot;

import cn.nukkit.Server;

import milk.telegram.handler.DefaultHandler;
import milk.telegram.handler.Handler;
import milk.telegram.media.Update;
import milk.telegram.media.chat.SuperGroup;
import milk.telegram.media.interfaces.Idable;
import milk.telegram.media.User;
import milk.telegram.media.message.Message;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends Thread{

    private String token = "";

    private int lastId = 0;
    private int timeout = 1000;

    private Handler handler = null;

    private User me;

    public TelegramBot(String token){
        this(token, new DefaultHandler());
    }

    public TelegramBot(String token, Handler handler){
        this(token, handler, 1000);
    }

    public TelegramBot(String token, Handler handler, int timeout){
        this.setToken(token);
        this.setTimeout(timeout);
        this.setHandler(handler);
    }

    public final JSONObject updateResponse(String key, JSONObject object){
        try{
            URL url = new URL(String.format("https://api.telegram.org/bot%s/%s", this.token, key));
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(this.timeout);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            if(object != null && object.length() > 0){
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

    public void run(){
        while(true){
            if(Thread.interrupted() || this.token.length() < 45 || this.handler == null) break;

            try{
                if(this.handler.getBot() != this){
                    this.handler.setBot(this);
                }

                JSONObject k = new JSONObject();
                if(this.lastId > 0){
                    k.put("offset", this.lastId + 1);
                }
                JSONObject update = this.updateResponse("getUpdates", k);
                if(update == null){
                    continue;
                }

                JSONArray array = update.optJSONArray("result");
                if(array == null){
                    continue;
                }

                List<Update> list = new ArrayList<>();
                for(int i = 0; i < array.length(); i++){
                    Update kkk = Update.create(array.optJSONObject(i));
                    if(kkk == null){
                        continue;
                    }
                    list.add(kkk);
                    this.lastId = kkk.getId();
                }
                this.handler.update(list);
            }catch(Exception e){}
        }
    }

    public User getMe(){
        if(this.me == null){
            JSONObject ob = updateResponse("getMe", null);
            if(ob != null && ob.has("result")){
                this.me = User.create(ob.getJSONObject("result"));
            }else{
                return null;
            }
        }
        return this.me;
    }

    public void setToken(String token){
        if(token.length() != 45){
            return;
        }
        this.token = token;
    }

    public void setTimeout(int time){
        this.timeout = Math.max(time, 500);
    }

    public void setHandler(Handler handler){
        if(handler == null){
            handler = new DefaultHandler();
        }
        handler.setBot(this);
        this.handler = handler;
    }

    public Message sendMessage(String text, Object chat_id){
        return sendMessage(text, chat_id, null);
    }

    public Message sendMessage(String text, Object chat, Object reply_message){
        return sendMessage(text, chat, reply_message, null);
    }

    public Message sendMessage(String text, Object chat, Object reply_message, String parse_mode){
        return sendMessage(text, chat, reply_message, parse_mode, null);
    }

    public Message sendMessage(String text, Object chat, Object reply_message, String parse_mode, Boolean disable_web){
        return sendMessage(text, chat, reply_message, parse_mode, disable_web, null);
    }

    public Message sendMessage(String text, Object chat, Object reply_message, String parse_mode, Boolean disable_web, Boolean disable_noti){
        if(chat instanceof SuperGroup){
            chat = ((SuperGroup) chat).getUsername();
        }else if(chat instanceof Idable){
            chat = ((Idable) chat).getId();
        }else if(!(chat instanceof String || chat instanceof Integer)){
            return null;
        }
        
        if(reply_message instanceof Message){
            reply_message = ((Message) reply_message).getId();
        }else if(reply_message != null && !(reply_message instanceof Integer)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("text", text);
        object.put("chat_id", chat);
        if(parse_mode != null) object.put("parse_mode", parse_mode);
        if(disable_noti != null) object.put("disable_notification", disable_noti);
        if(reply_message != null) object.put("reply_to_message_id", reply_message);
        if(disable_web != null) object.put("disable_web_page_preview", disable_web);

        JSONObject ob = updateResponse("sendMessage", object);
        if(ob != null && ob.has("result")){
            return Message.create(ob.getJSONObject("result"));
        }
        return null;
    }

}