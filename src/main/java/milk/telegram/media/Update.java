package milk.telegram.media;

import milk.telegram.media.interfaces.Idable;
import milk.telegram.media.message.Message;
import org.json.JSONObject;

public class Update implements Idable<Integer>{

    private final int id;

    private final Message message;
    private final Message edited_message;

    /*private final InlineQuery inline_query;
    private final CallbackQuery callback_query;

    private final ChosenInlineResult chosen_inline_result;*/

    private Update(JSONObject object){
        this.id = object.getInt("update_id");
        this.message = Message.create(object.optJSONObject("message"));
        this.edited_message = Message.create(object.optJSONObject("edited_message"));

        //TODO
        /*this.inline_query = InlineQuery.create(object.optJSONObject("inline_query"));
        this.callback_query = CallbackQuery.create(object.optJSONObject("callback_query"));

        this.chosen_inline_result = ChosenInlineResult.create(object.optJSONObject("chosen_inline_result"));*/
    }

    public static Update create(JSONObject object){
        if(object == null || !object.has("update_id") || object.length() < 2){
            return null;
        }
        return new Update(object);
    }

    public Integer getId(){
        return id;
    }

    public Message getMessage(){
        return message;
    }

    public Message getEditMessage(){
        return edited_message;
    }
}
