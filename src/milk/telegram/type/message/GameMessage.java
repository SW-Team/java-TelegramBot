package milk.telegram.type.message;

import milk.telegram.type.game.Game;
import org.json.JSONObject;

public class GameMessage extends Message{

    private final Game game;

    protected GameMessage(JSONObject object){
        super(object);
        this.game = Game.create(object.getJSONObject("game"));
    }

    public Game getGame(){
        return game;
    }

    public String getName(){
        return "게임";
    }

}
