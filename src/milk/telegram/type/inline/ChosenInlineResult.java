package milk.telegram.type.inline;

import milk.telegram.type.Identifier;
import milk.telegram.type.file.Location;
import milk.telegram.type.user.User;
import org.json.JSONObject;

public class ChosenInlineResult implements Identifier<String>{

    private final String id;
    private final User from;
    private final Location location;

    private final String query;
    private final String inline_message_id;

    private ChosenInlineResult(JSONObject object){
        this.id = object.getString("result_id");
        this.from = User.create(object.getJSONObject("from"));
        this.location = Location.create(object.optJSONObject("location"));

        this.query = object.getString("query");
        this.inline_message_id = object.optString("inline_message_id");
    }

    public static ChosenInlineResult create(JSONObject object){
        if(object == null){
            return null;
        }
        return new ChosenInlineResult(object);
    }

    public String getId(){
        return id;
    }

    public User getFrom(){
        return from;
    }

    public String getQuery(){
        return query;
    }

    public String getInlineId(){
        return inline_message_id;
    }

    public Location getLocation(){
        return location;
    }

}
