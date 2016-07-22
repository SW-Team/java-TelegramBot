package com.milk.telegram.event;

import cn.nukkit.event.Event;
import com.milk.telegram.media.message.Message;

public class TelegramMessageReceiveEvent extends Event{

    private final Message message;

    public TelegramMessageReceiveEvent(Message msg){
        this.message = msg;
    }

    public Message getMessage(){
        return this.message;
    }

}