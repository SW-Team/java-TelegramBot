package milk.telegram.type.reply;

import org.json.JSONObject;

public class ForceReply extends ReplyMarkup{

    private Boolean selective;

    public ForceReply(){
        this.put("force_reply", true);
    }

    public Boolean getSelective(){
        return this.optBoolean("selective");
    }

    public void setSelective(boolean selective){
        this.put("selective", selective);
    }
}
