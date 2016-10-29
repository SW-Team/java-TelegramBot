package milk.telegram.type.reply;

import milk.telegram.type.inline.InlineKeyboardButton;

import org.json.JSONArray;

public class InlineKeyboardMarkup extends ReplyMarkup{

    public InlineKeyboardMarkup(){
        this.put("inline_keyboard", new JSONArray());
    }

    public InlineKeyboardButton getButton(int column, int row){
        return null;
    }

    public void setButton(int column, int row, InlineKeyboardButton button){
        JSONArray keyboard = this.getJSONArray("inline_keyboard");
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
