package milk.telegram.type.reply;

import org.json.JSONArray;
import org.json.JSONObject;

public class InlineKeyboardMarkup implements ReplyMarkup{

    private JSONArray inline_keyboard; //Array of Array of InlineKeyboardButton(JSONObject, Not API's Class)

    public InlineKeyboardMarkup(JSONArray keyboard){
        this.inline_keyboard = keyboard;
    }

    public JSONArray getInlineKeyboard(){
        return inline_keyboard;
    }

    public void setInlineKeyboard(JSONArray inline_keyboard){
        this.inline_keyboard = inline_keyboard;
    }

    public JSONObject getJsonData(){
        JSONObject object = new JSONObject();
        object.put("inline_keyboard", this.inline_keyboard);
        return object;
    }

}
