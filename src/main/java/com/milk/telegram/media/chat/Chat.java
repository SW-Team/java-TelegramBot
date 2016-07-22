package com.milk.telegram.media.chat;

import com.milk.telegram.media.interfaces.IntegerId;
import org.json.JSONObject;

public abstract class Chat implements IntegerId{

    private int id;

    private String type;

    public Chat(JSONObject object){
        this.id = object.getInt("id");
        this.type = object.getString("type");
    }

    public static Chat create(JSONObject object){
        switch(object.getString("type")){
            case "private":
                return new PrivateChat(object);
            case "group":
                return new Group(object);
            case "channel":
                return new Channel(object);
            case "supergroup":
                return new SuperGroup(object);
        }
        return null;
    }

    public int getId(){
        return this.id;
    }

    public String getType(){
        return this.type;
    }

}
