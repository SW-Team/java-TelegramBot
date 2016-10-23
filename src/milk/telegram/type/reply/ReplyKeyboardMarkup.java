package milk.telegram.type.reply;

import milk.telegram.type.inline.InlineKeyboardButtonArray;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReplyKeyboardMarkup implements ReplyMarkup{

    private JSONArray keyboard;

    private Boolean selective;
    private Boolean resize_keyboard;
    private Boolean one_time_keyboard;

    public ReplyKeyboardMarkup(InlineKeyboardButtonArray keyboard){
        this.keyboard = keyboard;
    }

    public JSONArray getKeyboard(){
        return keyboard;
    }

    public Boolean getSelective(){
        return selective;
    }

    public Boolean getResizeKeyboard(){
        return resize_keyboard;
    }

    public Boolean getOneTimeKeyboard(){
        return one_time_keyboard;
    }

    public void setSelective(Boolean selective){
        this.selective = selective;
    }

    public void setKeyboard(InlineKeyboardButtonArray keyboard){
        this.keyboard = keyboard;
    }

    public void setOneTimeKeyboard(Boolean selective){
        this.one_time_keyboard = selective;
    }

    public void setResizeKeyboard(Boolean resize_keyboard){
        this.resize_keyboard = resize_keyboard;
    }

    public JSONObject toJSONObject(){
        JSONObject object = new JSONObject();
        object.put("force_reply", true);
        if(this.selective != null) object.put("selective", this.selective);
        return object;
    }

}
