package milk.telegram.method.replier;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.callback.CallbackQuery;
import org.json.JSONObject;

public class CallbackReplier extends Replier{

    public CallbackReplier(TelegramBot bot){
        super(bot);
    }

    public String getUrl(){
        return optString("url");
    }

    public String getText(){
        return optString("text");
    }

    public boolean isShowAlert(){
        return optBoolean("show_alert");
    }

    public String getQueryId(){
        return optString("query_id");
    }

    public CallbackReplier setUrl(String url){
        this.put("url", url);
        return this;
    }

    public CallbackReplier setText(String text){
        this.put("text", text);
        return this;
    }

    public CallbackReplier setShowAlert(boolean show_alert){
        this.put("show_alert", show_alert);
        return this;
    }

    public CallbackReplier setQueryId(Object query_id){
        if(query_id instanceof CallbackQuery){
            this.put("query_id", ((CallbackQuery) query_id).getId());
        }else if(query_id instanceof String){
            this.put("query_id", query_id);
        }
        return this;
    }

    public Boolean send(){
        JSONObject ob = bot.updateResponse("answerCallbackQuery", this);
        return ob != null && ob.optBoolean("result");
    }
}
