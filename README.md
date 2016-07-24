# TelegramManager
  
Author(제작자): **[SW-Team](https://github.com/SW-Team)**
  
##Usage
```java
Bot bot = new Bot("Bot::TOKEN");
bot.run();
```
  
##Message Receiving
``` java
@EventHandler
public void onReceiveEvent(TelegramMessageReceiveEvent ev){
    Bot bot = ev.getBot(); //메시지를 받은 봇
    Message msg = ev.getMessage(); //메시지 객체
}

@EventHandler
public void onReceiveEvent(TelegramCommandReceiveEvent ev){
    Bot bot = ev.getBot(); //메시지를 받은 봇
    String command = ev.getCommand(); //실행한 커맨드
    String[] args = ev.getArgs();
    Message msg = ev.getMessage(); //메시지 객체
}
```