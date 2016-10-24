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
        if(this.length() < column){
            column = this.length();
        }
        JSONArray array = this.getJSONArray("inline_keyboard").optJSONArray(column);
        if(array == null) this.getJSONArray("inline_keyboard").put(column, array = new JSONArray());
        if(array.length() < row){
            row = array.length();
        }
        array.put(row, button);
    }

}
