package milk.telegram.media.user;

import milk.telegram.media.interfaces.Idable;
import milk.telegram.media.interfaces.Usernamed;
import org.json.JSONObject;

public class User implements Idable<Integer>, Usernamed{

    private int id;

    private String last;
    private String first;
    private String username;

    private User(JSONObject object){
        this.id = object.getInt("id");
        this.last = object.optString("last_name", null);
        this.first = object.optString("first_name", null);
        this.username = object.optString("username", null);
    }

    public static User create(JSONObject object){
        if(object == null){
            return null;
        }
        return new User(object);
    }

    public Integer getId(){
        return this.id;
    }

    public String getFirstName(){
        return this.first;
    }

    public String getLastName(){
        return this.last;
    }

    public String getUsername(){
        return this.username;
    }

    public String getFullName(){
        return (this.getLastName() == null ? "" : this.getLastName() + " ") + this.getFirstName();
    }

}
