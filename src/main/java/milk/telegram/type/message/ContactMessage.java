package milk.telegram.type.message;

import milk.telegram.type.file.Contact;
import org.json.JSONObject;

public class ContactMessage extends Message{

    private Contact contact;

    public ContactMessage(JSONObject object){
        super(object);
        this.contact = Contact.create(object.getJSONObject("contact"));
    }

    public Contact getContact(){
        return this.contact;
    }

    public String getName(){
        return "연락처";
    }

}
