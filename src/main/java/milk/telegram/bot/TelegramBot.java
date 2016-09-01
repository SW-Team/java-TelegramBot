package milk.telegram.bot;

import milk.telegram.handler.DefaultHandler;
import milk.telegram.handler.Handler;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.file.photo.UserProfilePhotos;
import milk.telegram.type.interfaces.Usernamed;
import milk.telegram.update.Update;
import milk.telegram.type.chat.SuperGroup;
import milk.telegram.type.interfaces.Idable;
import milk.telegram.type.message.StickerMessage;
import milk.telegram.type.message.TextMessage;
import milk.telegram.type.file.photo.Sticker;
import milk.telegram.type.user.User;
import milk.telegram.type.message.Message;

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
    private int limit = 100;
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
            e.printStackTrace();
            return null;
        }
    }

    public void run(){
        while(true){
            if(this.isInterrupted() || this.token.length() < 45 || this.handler == null) break;

            try{
                if(this.handler.getBot() != this){
                    this.handler.setBot(this);
                }

                JSONObject k = new JSONObject();
                k.put("limit", this.getLimit());
                if(this.lastId > 0) k.put("offset", this.lastId + 1);

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
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public User getMe(){
        if(this.me == null){
            this.me = User.create(updateResponse("getMe", null));
        }
        return this.me;
    }

    public int getLimit(){
        return limit;
    }

    public void setToken(String token){
        if(token.length() != 45){
            return;
        }
        this.token = token;
        this.me = User.create(updateResponse("getMe", null));
    }

    public void setLimit(int value){
        this.limit = Math.max(1, Math.min(100, value));
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

    public void sendChatAction(String action, Object chat){
        if(chat instanceof Idable){
            chat = (chat instanceof SuperGroup || chat instanceof Channel) ? "@" + ((Usernamed) chat).getUsername() : ((Idable) chat).getId();
        }else if(!(chat instanceof String || chat instanceof Integer)){
            return;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("action", action);
        this.updateResponse("sendChatAction", object);
    }

    public TextMessage sendMessage(String text, Object chat){
        return sendMessage(text, chat, null);
    }

    public TextMessage sendMessage(String text, Object chat, Object reply_message){
        return sendMessage(text, chat, reply_message, null);
    }

    public TextMessage sendMessage(String text, Object chat, Object reply_message, String parse_mode){
        return sendMessage(text, chat, reply_message, parse_mode, null);
    }

    public TextMessage sendMessage(String text, Object chat, Object reply_message, String parse_mode, Boolean disable_web){
        return sendMessage(text, chat, reply_message, parse_mode, disable_web, null);
    }

    public TextMessage sendMessage(String text, Object chat, Object reply_message, String parse_mode, Boolean disable_web, Boolean disable_noti){
        if(chat instanceof Idable){
            chat = (chat instanceof SuperGroup || chat instanceof Channel) ? "@" + ((Usernamed) chat).getUsername() : ((Idable) chat).getId();
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

        return (TextMessage) Message.create(updateResponse("sendMessage", object));
    }

    public Message forwardMessage(Object message, Object chat, Object chat_from){
        return forwardMessage(message, chat, chat_from, null);
    }

    public Message forwardMessage(Object message, Object chat, Object chat_from, Boolean disable_noti){
        if(message instanceof Message){
            message = ((Message) message).getId();
        }else if(message != null && !(message instanceof Integer)){
            return null;
        }

        if(chat instanceof Idable){
            chat = (chat instanceof SuperGroup || chat instanceof Channel) ? "@" + ((Usernamed) chat).getUsername() : ((Idable) chat).getId();
        }else if(!(chat instanceof String || chat instanceof Integer)){
            return null;
        }

        if(chat_from instanceof Idable){
            chat_from = (chat instanceof SuperGroup || chat instanceof Channel) ? ((Usernamed) chat_from).getUsername() : ((Idable) chat_from).getId();
        }else if(!(chat_from instanceof String || chat_from instanceof Integer)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("message_id", message);
        object.put("from_chat_id", chat_from);
        if(disable_noti != null) object.put("disable_notification", disable_noti);

        return Message.create(updateResponse("forwardMessage", object));
    }

    public StickerMessage sendSticker(Object sticker, Object chat){
        return sendSticker(sticker, chat, null);
    }

    public StickerMessage sendSticker(Object sticker, Object chat, Object reply_message){
        return sendSticker(sticker, chat, reply_message, null);
    }

    public StickerMessage sendSticker(Object sticker, Object chat, Object reply_message, Boolean disable_noti){
        if(sticker instanceof Sticker){
            sticker = ((Sticker) sticker).getId();
        }else if(!(sticker instanceof String)){
            return null;
        }

        if(chat instanceof Idable){
            chat = (chat instanceof SuperGroup || chat instanceof Channel) ? "@" + ((Usernamed) chat).getUsername() : ((Idable) chat).getId();
        }else if(!(chat instanceof String || chat instanceof Integer)){
            return null;
        }

        if(reply_message instanceof Message){
            reply_message = ((Message) reply_message).getId();
        }else if(reply_message != null && !(reply_message instanceof Integer)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("sticker", sticker);
        if(disable_noti != null) object.put("disable_notification", disable_noti);
        if(reply_message != null) object.put("reply_to_message_id", reply_message);

        return (StickerMessage) Message.create(updateResponse("sendSticker", object));
    }

    public UserProfilePhotos getUserProfilePhotos(Object user){
        return getUserProfilePhotos(user, null);
    }

    public UserProfilePhotos getUserProfilePhotos(Object user, Integer offset){
        return getUserProfilePhotos(user, null, null);
    }

    public UserProfilePhotos getUserProfilePhotos(Object user, Integer offset, Integer limit){
        if(user instanceof User){
            user = ((User) user).getId();
        }else if(!(user instanceof Integer)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("limit", limit);
        object.put("user_id", user);
        object.put("offset", offset);
        return UserProfilePhotos.create(updateResponse("getUserProfilePhotos", object));
    }

    public boolean kickChatMember(Object chat, Object user){
        if(chat instanceof Idable){
            chat = (chat instanceof SuperGroup || chat instanceof Channel) ? "@" + ((Usernamed) chat).getUsername() : ((Idable) chat).getId();
        }else if(!(chat instanceof String || chat instanceof Integer)){
            return false;
        }

        if(user instanceof User){
            user = ((User) user).getId();
        }else if(!(user instanceof Integer)){
            return false;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("user_id", user);
        JSONObject ob = updateResponse("kickChatMember", object);
        return ob != null && ob.optBoolean("result");
    }

    public boolean leaveChat(Object chat){
        if(chat instanceof Idable){
            chat = (chat instanceof SuperGroup || chat instanceof Channel) ? "@" + ((Usernamed) chat).getUsername() : ((Idable) chat).getId();
        }else if(!(chat instanceof String || chat instanceof Integer)){
            return false;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        JSONObject ob = updateResponse("leaveChat", object);
        return ob != null && ob.optBoolean("result");
    }
}