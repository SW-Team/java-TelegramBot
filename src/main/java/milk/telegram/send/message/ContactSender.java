package milk.telegram.send.message;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.message.ContactMessage;
import milk.telegram.type.message.Message;
import org.json.JSONObject;

public class ContactSender extends MessageSender{

    private String phone_number;

    private String last_name;
    private String first_name;

    public ContactSender(TelegramBot bot){
        super(bot);
    }

    @Override
    public ContactMessage send(){
        JSONObject object = new JSONObject();
        object.put("chat_id", chat_id);
        object.put("first_name", first_name);
        object.put("phone_number", phone_number);
        object.put("disable_notification", disable_notification);

        if(last_name != null) object.put("last_name", last_name);
        if(message_id != -1) object.put("reply_to_message_id", message_id);

        return (ContactMessage) Message.create(bot.updateResponse("sendContact", object));
    }

}
