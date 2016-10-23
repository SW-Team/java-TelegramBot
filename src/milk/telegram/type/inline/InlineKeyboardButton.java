package milk.telegram.type.inline;

import org.json.JSONObject;

public class InlineKeyboardButton extends JSONObject{

    public void setText(String text){
        this.put("text", text);
    }

    public void setUrl(String url){
        this.put("url", url);
    }

    public void setCallbackGame(Object callback_game){
        this.put("callback_game", callback_game);
    }

    public void setCallbackData(String callback_data){
        this.put("callback_data", callback_data);
    }

    public void setSwitchInlineQuery(String switch_inline_query){
        this.put("switch_inline_query", switch_inline_query);
    }

    public void setSwitchInlineQueryCurrentChat(String switch_inline_query_current_chat){
        this.put("switch_inline_query_current_chat", switch_inline_query_current_chat);
    }

}
