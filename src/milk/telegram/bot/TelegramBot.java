package milk.telegram.bot;

import milk.telegram.type.callback.CallbackQuery;
import milk.telegram.handler.Handler;
import milk.telegram.type.chat.Channel;
import milk.telegram.type.file.photo.UserProfilePhotos;
import milk.telegram.type.Usernamed;
import milk.telegram.type.game.GameHighScore;
import milk.telegram.type.reply.InlineKeyboardMarkup;
import milk.telegram.type.message.GameMessage;
import milk.telegram.type.user.ChatMember;
import milk.telegram.update.Update;
import milk.telegram.type.Identifier;
import milk.telegram.type.message.TextMessage;
import milk.telegram.type.user.User;
import milk.telegram.type.message.Message;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends Thread{

    public static final String BASE_URL = "https://api.telegram.org/bot%s/%s";

    private String token = "";

    private int lastId = 0;
    private int limit = 100;
    private int timeout = 1500;

    private User me;

    private Handler handler;

    public TelegramBot(String token){
        this(token, null);
    }

    public TelegramBot(String token, Handler handler){
        this(token, handler, 1000);
    }

    public TelegramBot(String token, Handler handler, int timeout){
        this.setToken(token);
        this.setHandler(handler);
        this.setTimeout(timeout);
    }

    public final JSONObject updateResponse(String key, JSONObject object){
        try{
            URL url = new URL(String.format(BASE_URL, this.token, key));
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(this.timeout);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            if(object != null && object.length() > 0){
                connection.setDoOutput(true);
                OutputStream stream = connection.getOutputStream();
                stream.write(object.toString().getBytes(StandardCharsets.UTF_8));
            }

            return new JSONObject(new JSONTokener(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)));
        }catch(IOException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Object fixChat(Object chat){
        if(chat instanceof Identifier){
            chat = chat instanceof Channel ? "@" + ((Usernamed) chat).getUsername() : ((Identifier) chat).getId();
        }
        return chat instanceof String || chat instanceof Number ? chat : null;
    }

    public void run(){
        while(true){
            if(this.isInterrupted() || this.token.length() < 45 || this.handler == null) break;

            try{
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

    public int getTimeout(){
        return this.timeout;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        if(token.length() != 45){
            return;
        }
        this.me = null;
        this.token = token;
    }

    /** setMethod **/
    public void setLimit(int value){
        this.limit = Math.max(1, Math.min(100, value));
    }

    public void setTimeout(int time){
        this.timeout = Math.max(time, 500);
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public GameMessage setGameScore(long score, Object user, Object chat, Object message){
        if(message instanceof Message){
            chat = ((Message) message).getChat().getId();
            message = ((Message) message).getId();
        }else if(message != null && !(message instanceof Number) && !(message instanceof String)){
            return null;
        }

        if(user instanceof User){
            user = ((User) user).getId();
        }else if(user != null && !(user instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("score", score);
        object.put("user_id", user);
        object.put("chat_id", chat);
        object.put("message_id", message);

        return (GameMessage) Message.create(updateResponse("setGameScore", object));
    }

    public GameMessage setGameScore(long score, Object user, Object message){
        Object chat = null;
        if(message instanceof Message){
            chat = ((Message) message).getChat().getId();
            message = ((Message) message).getId();
        }else if(message != null && !(message instanceof Number) && !(message instanceof String)){
            return null;
        }

        if(user instanceof User){
            user = ((User) user).getId();
        }else if(user != null && !(user instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("score", score);
        object.put("user_id", user);
        if(chat != null){
            object.put("chat_id", chat);
            object.put("message_id", message);
        }else{
            object.put("inline_message_id", message);
        }

        return (GameMessage) Message.create(updateResponse("setGameScore", object));
    }
    /** setMethod **/

    /** sendMethod */
    public GameMessage sendGame(Object game, Object chat){
        return sendGame(game, chat, null);
    }

    public GameMessage sendGame(Object game, Object chat, Object reply_message){
        return sendGame(game, chat, reply_message, null);
    }

    public GameMessage sendGame(Object game, Object chat, Object reply_message, InlineKeyboardMarkup reply_markup){
        return sendGame(game, chat, reply_message, reply_markup, null);
    }

    public GameMessage sendGame(Object game, Object chat, Object reply_message, InlineKeyboardMarkup reply_markup, Boolean disable_noti){
        if((chat = fixChat(chat)) == null){
            return null;
        }

        if(game instanceof CallbackQuery){
            game = ((CallbackQuery) reply_message).getGameShortName();
        }else if(game != null && !(game instanceof String)){
            return null;
        }

        if(reply_message instanceof Message){
            reply_message = ((Message) reply_message).getId();
        }else if(reply_message != null && !(reply_message instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("game_short_name", game);
        if(disable_noti != null) object.put("disable_notification", disable_noti);
        if(reply_message != null) object.put("reply_to_message_id", reply_message);
        if(reply_markup != null) object.put("reply_markup", reply_markup.getJsonData());

        return (GameMessage) Message.create(updateResponse("sendGame", object));
    }

    public void sendChatAction(String action, Object chat){
        if((chat = fixChat(chat)) == null){
            return;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("action", action);
        this.updateResponse("sendChatAction", object);
    }
    /** sendMethod */

    /** getMethod */
    public ChatMember getChatMember(Object chat, Object user){
        if((chat = fixChat(chat)) == null){
            return null;
        }

        if(user instanceof User){
            user = ((User) user).getId();
        }else if(!(user instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("user_id", user);
        return ChatMember.create(updateResponse("getChatMember", object));
    }

    public Integer getChatMembersCount(Object chat){
        if((chat = fixChat(chat)) == null){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        JSONObject ob = updateResponse("getChatMembersCount", object);
        return (ob == null || !ob.has("result")) ? null : ob.optInt("result");
    }

    public ArrayList<ChatMember> getChatAdministrators(Object chat){
        if((chat = fixChat(chat)) == null){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);

        JSONObject ob = updateResponse("getChatAdministrators", object);
        if(ob != null && ob.has("result")){
            JSONArray ar = ob.getJSONArray("result");
            ArrayList<ChatMember> array = new ArrayList<>();
            for(Object obj : ar) array.add(ChatMember.create((JSONObject) obj));
            return array;
        }
        return null;
    }

    public GameHighScore getGameHighScores(Object chat, Object user, Object message){
        //TODO: 개 귀찮아
        return null;
    }

    public GameHighScore getGameHighScores(Object user, Object inline){
        //TODO: 개 귀찮아
        return null;
    }

    public UserProfilePhotos getUserProfilePhotos(Object user){
        return getUserProfilePhotos(user, null);
    }

    public UserProfilePhotos getUserProfilePhotos(Object user, Number offset){
        return getUserProfilePhotos(user, offset, null);
    }

    public UserProfilePhotos getUserProfilePhotos(Object user, Number offset, Number limit){
        if(user instanceof User){
            user = ((User) user).getId();
        }else if(!(user instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("limit", limit);
        object.put("user_id", user);
        object.put("offset", offset);
        return UserProfilePhotos.create(updateResponse("getUserProfilePhotos", object));
    }
    /** getMethod **/

    /** editMethod **/
    public TextMessage editMessageText(String text, Object chat, Object message){
        return editMessageText(text, chat, message, null);
    }

    public TextMessage editMessageText(String text, Object chat, Object message, String parse_mode){
        return editMessageText(text, chat, message, parse_mode, null);
    }

    public TextMessage editMessageText(String text, Object chat, Object message, String parse_mode, Boolean disable_web){
        if((chat = fixChat(chat)) == null){
            return null;
        }

        if(message instanceof Message){
            message = ((Message) message).getId();
        }else if(message != null && !(message instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("text", text);
        object.put("chat_id", chat);
        object.put("message_id", message);
        if(parse_mode != null) object.put("parse_mode", parse_mode);
        if(disable_web != null) object.put("disable_web_page_preview", disable_web);

        return (TextMessage) Message.create(updateResponse("editMessageText", object));
    }

    public TextMessage editMessageText(String text, String inline, String parse_mode, Boolean disable_web){
        JSONObject object = new JSONObject();
        object.put("text", text);
        object.put("inline_message_id", inline);
        if(parse_mode != null) object.put("parse_mode", parse_mode);
        if(disable_web != null) object.put("disable_web_page_preview", disable_web);

        return (TextMessage) Message.create(updateResponse("editMessageText", object));
    }

    public TextMessage editMessageCaption(String caption, Object chat, Object message){
        if((chat = fixChat(chat)) == null){
            return null;
        }

        if(message instanceof Message){
            message = ((Message) message).getId();
        }else if(message != null && !(message instanceof Number)){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("caption", caption);
        object.put("chat_id", chat);
        object.put("message_id", message);
        return (TextMessage) Message.create(updateResponse("editMessageCaption", object));
    }

    public TextMessage editMessageCaption(String caption, String inline){
        JSONObject object = new JSONObject();
        object.put("caption", caption);
        object.put("inline_message_id", inline);
        return (TextMessage) Message.create(updateResponse("editMessageCaption", object));
    }
    /** editMethod **/

    /** anotherMethod */
    public Message forwardMessage(Object message, Object chat, Object chat_from){
        return forwardMessage(message, chat, chat_from, null);
    }

    public Message forwardMessage(Object message, Object chat, Object chat_from, Boolean disable_noti){
        if(message instanceof Message){
            message = ((Message) message).getId();
        }else if(message != null && !(message instanceof Number)){
            return null;
        }

        if((chat = fixChat(chat)) == null || (chat_from = fixChat(chat_from)) == null){
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("message_id", message);
        object.put("from_chat_id", chat_from);
        if(disable_noti != null) object.put("disable_notification", disable_noti);

        return Message.create(updateResponse("forwardMessage", object));
    }

    public boolean kickChatMember(Object user, Object chat){
        if((chat = fixChat(chat)) == null){
            return false;
        }

        if(user instanceof User){
            user = ((User) user).getId();
        }else if(!(user instanceof Number)){
            return false;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("user_id", user);
        JSONObject ob = updateResponse("kickChatMember", object);
        return ob != null && ob.optBoolean("result");
    }

    public boolean unbanChatMember(Object user, Object chat){
        if((chat = fixChat(chat)) == null){
            return false;
        }

        if(user instanceof User){
            user = ((User) user).getId();
        }else if(!(user instanceof Number)){
            return false;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        object.put("user_id", user);
        JSONObject ob = updateResponse("unbanChatMember", object);
        return ob != null && ob.optBoolean("result");
    }

    public boolean leaveChat(Object chat){
        if((chat = fixChat(chat)) == null){
            return false;
        }

        JSONObject object = new JSONObject();
        object.put("chat_id", chat);
        JSONObject ob = updateResponse("leaveChat", object);
        return ob != null && ob.optBoolean("result");
    }

}