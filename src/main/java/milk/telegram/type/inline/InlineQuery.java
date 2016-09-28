package milk.telegram.type.inline;

import milk.telegram.type.Identifier;
import milk.telegram.type.file.Location;
import milk.telegram.type.user.User;

import org.json.JSONObject;

public class InlineQuery implements Identifier<String>{

    private final String id;
    private final User from;
    private final Location location;

    private final String query;
    private final String offset;

    private InlineQuery(JSONObject object){
        this.id = object.getString("id");
        this.from = User.create(object.getJSONObject("from"));
        this.location = Location.create(object.optJSONObject("location"));

        this.query = object.getString("query");
        this.offset = object.getString("offset");
    }

    public static InlineQuery create(JSONObject object){
        if(object == null){
            return null;
        }
        return new InlineQuery(object);
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

    public String getOffset(){
        return offset;
    }

    public Location getLocation(){
        return location;
    }

}
