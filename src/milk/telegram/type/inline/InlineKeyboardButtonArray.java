package milk.telegram.type.inline;

import org.json.JSONArray;

public class InlineKeyboardButtonArray extends JSONArray{

    public void setButton(int column, int row, InlineKeyboardButton button){
        if(this.length() < column){
            column = this.length();
        }
        JSONArray array = this.optJSONArray(column);
        if(array == null) this.put(column, array = new JSONArray());
        if(array.length() < row){
            row = array.length();
        }
        array.put(row, button);
    }

}