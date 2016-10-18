package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Contact;
import milk.telegram.type.message.ContactMessage;
import milk.telegram.type.message.Message;
import org.json.JSONObject;

public class ContactSender extends Sender{

    protected String phone_number;

    protected String last_name;
    protected String first_name;

    public ContactSender(TelegramBot bot){
        super(bot);
    }

    public ContactSender setContact(Contact contact){
        this.last_name = contact.getLastName();
        this.first_name = contact.getFirstName();
        this.phone_number = contact.getPhoneNumber();
        return this;
    }

    public ContactSender setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
        return this;
    }

    public ContactSender setLastName(String last_name){
        this.last_name = last_name;
        return this;
    }

    public ContactSender setFirstName(String first_name){
        this.first_name = first_name;
        return this;
    }

    @Override
    public ContactSender setChatId(Object chat_id){
        return (ContactSender) super.setChatId(chat_id);
    }

    @Override
    public ContactSender setMessageId(Object message_id){
        return (ContactSender) super.setMessageId(message_id);
    }

    @Override
    public ContactSender setDisableNotification(boolean value){
        return (ContactSender) super.setDisableNotification(value);
    }

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
