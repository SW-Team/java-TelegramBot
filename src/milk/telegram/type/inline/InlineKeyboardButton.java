package milk.telegram.type.inline;

import org.json.JSONObject;

public class InlineKeyboardButton extends JSONObject{

    public InlineKeyboardButton setText(String text){
        this.put("text", text);
        return this;
    }

    public InlineKeyboardButton setUrl(String url){
        this.put("url", url);
        return this;
    }

    public InlineKeyboardButton setCallbackGame(Object callback_game){
        this.put("callback_game", callback_game);
        return this;
    }

    public InlineKeyboardButton setCallbackData(String callback_data){
        this.put("callback_data", callback_data);
        return this;
    }

    public InlineKeyboardButton setSwitchInlineQuery(String switch_inline_query){
        this.put("switch_inline_query", switch_inline_query);
        return this;
    }

    public InlineKeyboardButton setSwitchInlineQueryCurrentChat(String switch_inline_query_current_chat){
        this.put("switch_inline_query_current_chat", switch_inline_query_current_chat);
        return this;
    }

}
