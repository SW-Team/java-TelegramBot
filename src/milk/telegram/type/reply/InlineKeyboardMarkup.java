package milk.telegram.type.reply;

import milk.telegram.type.inline.InlineKeyboardButtonArray;
import org.json.JSONArray;
import org.json.JSONObject;

public class InlineKeyboardMarkup implements ReplyMarkup{

    private InlineKeyboardButtonArray inline_keyboard;

    public InlineKeyboardMarkup(InlineKeyboardButtonArray keyboard){
        this.inline_keyboard = keyboard;
    }

    public JSONArray getInlineKeyboard(){
        return inline_keyboard;
    }

    public void setInlineKeyboard(InlineKeyboardButtonArray inline_keyboard){
        this.inline_keyboard = inline_keyboard;
    }

    public JSONObject toJSONObject(){
        JSONObject object = new JSONObject();
        object.put("inline_keyboard", this.inline_keyboard);
        return object;
    }

}
