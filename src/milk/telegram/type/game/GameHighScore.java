package milk.telegram.type.game;

import milk.telegram.type.user.User;
import org.json.JSONObject;

public class GameHighScore{

    private final long score;
    private final long position;

    private final User user;

    private GameHighScore(JSONObject object){
        this.score = object.getLong("score");
        this.position = object.getLong("position");

        this.user = User.create(object.getJSONObject("user"));
    }

    public static GameHighScore create(JSONObject object){
        if(object == null){
            return null;
        }
        return new GameHighScore(object);
    }

    public long getScore(){
        return score;
    }

    public long getPosition(){
        return position;
    }

    public User getUser(){
        return user;
    }

}
