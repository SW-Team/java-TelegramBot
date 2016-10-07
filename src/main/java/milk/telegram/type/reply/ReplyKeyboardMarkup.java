package milk.telegram.type.reply;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReplyKeyboardMarkup implements ReplyMarkup{

    private JSONArray keyboard; //Array of Array of KeyboardButton(JSONObject, Not API's Class)

    private Boolean selective;
    private Boolean resize_keyboard;
    private Boolean one_time_keyboard;

    public ReplyKeyboardMarkup(JSONArray keyboard){
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

    public void setKeyboard(JSONArray keyboard){
        this.keyboard = keyboard;
    }

    public void setResizeKeyboard(Boolean resize_keyboard){
        this.resize_keyboard = resize_keyboard;
    }

    public void setOneTimeKeyboard(Boolean selective){
        this.one_time_keyboard = selective;
    }

    @Override
    public JSONObject getJsonData(){
        JSONObject object = new JSONObject();
        object.put("force_reply", true);
        if(this.selective != null) object.put("selective", this.selective);
        return null;
    }

}
