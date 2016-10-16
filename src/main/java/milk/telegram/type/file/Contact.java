package milk.telegram.type.file;

import org.json.JSONObject;

public class Contact{

    private final String phone_number;
    private final String first_name;
    private final String last_name;

    private final Integer user_id;

    private Contact(JSONObject object){
        this.phone_number = object.getString("phone_number");

        this.first_name = object.getString("first_name");
        this.last_name = object.optString("last_name", "");

        this.user_id = object.has("user_id") ? object.getInt("user_id") : null;
    }

    public static Contact create(JSONObject object){
        if(object == null){
            return null;
        }else if(object.has("result")){
            object = object.optJSONObject("result");
            if(object == null || object.length() < 1){
                return null;
            }
        }
        return new Contact(object);
    }

    public Integer getUserId(){
        return user_id;
    }

    public String getLastName(){
        return last_name;
    }

    public String getFirstName(){
        return first_name;
    }

    public String getPhoneNumber(){
        return phone_number;
    }

}
