package milk.telegram.type.reply;

import milk.telegram.type.callback.KeyboardButton;

import org.json.JSONArray;

public class ReplyKeyboardMarkup extends ReplyMarkup{

    public ReplyKeyboardMarkup(){
        this.put("keyboard", new JSONArray());
    }

    public Boolean getSelective(){
        return this.optBoolean("selective");
    }

    public Boolean getResizeKeyboard(){
        return this.optBoolean("resize_keyboard");
    }

    public Boolean getOneTimeKeyboard(){
        return this.optBoolean("one_time_keyboard");
    }

    public KeyboardButton getButton(int column, int row){
        return null;
    }

    public void setSelective(boolean selective){
        this.put("selective", selective);
    }

    public void setResizeKeyboard(boolean resize_keyboard){
        this.put("resize_keyboard", resize_keyboard);
    }

    public void setOneTimeKeyboard(boolean one_time_keyboard){
        this.put("one_time_keyboard", one_time_keyboard);
    }

    public void setButton(int column, int row, KeyboardButton button){
        JSONArray keyboard = this.getJSONArray("keyboard");
        if(keyboard.length() < column){
            column = keyboard.length();
        }
        JSONArray array = keyboard.optJSONArray(column);
        if(array == null) keyboard.put(column, array = new JSONArray());
        if(array.length() < row){
            row = array.length();
        }
        array.put(row, button);
    }

}
