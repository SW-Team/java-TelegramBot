package milk.telegram.method.sender;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.Contact;
import milk.telegram.type.message.Message;
import milk.telegram.type.reply.ReplyMarkup;
import milk.telegram.type.message.ContactMessage;

public class ContactSender extends Sender{

    public ContactSender(TelegramBot bot){
        super(bot);
    }

    public ContactSender setFirstName(String first_name){
        this.put("first_name", first_name);
        return this;
    }

    public ContactSender setPhoneNumber(String phone_number){
        this.put("phone_number", phone_number);
        return this;
    }

    //Optional
    public ContactSender setLastName(String last_name){
        this.put("last_name", last_name);
        return this;
    }

    //Optional
    public ContactSender setContact(Contact contact){
        this.put("last_name", contact.getLastName());
        this.put("first_name", contact.getFirstName());
        this.put("phone_number", contact.getPhoneNumber());
        return this;
    }

    public ContactSender setChatId(Object chat_id){
        return (ContactSender) super.setChatId(chat_id);
    }

    //Optional
    public ContactSender setMessageId(Object message_id){
        return (ContactSender) super.setMessageId(message_id);
    }

    //Optional
    public ContactSender setReplyMarkup(ReplyMarkup markup){
        return (ContactSender) super.setReplyMarkup(markup);
    }

    //Optional
    public ContactSender setDisableNotification(boolean value){
        return (ContactSender) super.setDisableNotification(value);
    }

    public ContactMessage send(){
        return (ContactMessage) Message.create(bot.updateResponse("sendContact", this));
    }

}
