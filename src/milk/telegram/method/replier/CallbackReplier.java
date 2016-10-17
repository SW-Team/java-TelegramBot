package milk.telegram.method.replier;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.callback.CallbackQuery;
import org.json.JSONObject;

public class CallbackReplier extends Replier{

    protected String url = null;
    protected String text = null;

    protected String query_id;

    protected boolean show_alert = false;

    public CallbackReplier(TelegramBot bot){
        super(bot);
    }

    public String getUrl(){
        return url;
    }

    public String getText(){
        return text;
    }

    public boolean isShowAlert(){
        return show_alert;
    }

    public String getQueryId(){
        return query_id;
    }

    public CallbackReplier setUrl(String url){
        this.url = url;
        return this;
    }

    public CallbackReplier setText(String text){
        this.text = text;
        return this;
    }

    public CallbackReplier setShowAlert(boolean show_alert){
        this.show_alert = show_alert;
        return this;
    }

    public CallbackReplier setQueryId(Object query_id){
        if(query_id instanceof CallbackQuery){
            this.query_id = ((CallbackQuery) query_id).getId();
        }else if(query_id instanceof String){
            this.query_id = (String) query_id;
        }
        return this;
    }

    public Boolean send(){
        JSONObject object = new JSONObject();
        if(this.url != null){
            object.put("url", url);
        }else if(this.text != null){
            object.put("text", text);
        }else{
            return false;
        }
        object.put("show_alert", show_alert);
        object.put("callback_query_id", query_id);

        JSONObject ob = bot.updateResponse("answerCallbackQuery", object);
        return ob != null && ob.optBoolean("result");
    }
}
