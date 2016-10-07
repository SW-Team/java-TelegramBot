package milk.telegram.type.reply;

import milk.telegram.type.ReplyMarkup;
import org.json.JSONObject;

public class ReplyKeyboardHide implements ReplyMarkup{

    private Boolean selective;

    public ReplyKeyboardHide(){
        this.selective = null;
    }

    public ReplyKeyboardHide(boolean selective){
        this.selective = selective;
    }

    public Boolean getSelective(){
        return selective;
    }

    public void setSelective(Boolean selective){
        this.selective = selective;
    }

    @Override
    public JSONObject getJsonData(){
        JSONObject object = new JSONObject();
        object.put("force_reply", true);
        if(this.selective != null) object.put("selective", this.selective);
        return null;
    }

}
