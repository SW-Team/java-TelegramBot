# TelegramManager
  
Author(제작자): **[SW-Team](https://github.com/SW-Team)**  

##Message Receiving
``` java
@EventHandler
public void onReceiveEvent(TelegramMessageReceiveEvent ev){
    Message msg = ev.getMessage(); //메시지 객체
    Chat chat = msg.getChat(); //메시지가 온 채팅방
    User user = msg.getFrom(); //메시지를 보낸 사람
}
```