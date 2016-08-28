# TelegramManager
  
Author(제작자): **[SW-Team](https://github.com/SW-Team)**
  
##Usage
```java
TelegramBot bot = new TelegramBot("Bot::TOKEN");
bot.start();
```
  
##Message Receiving
``` java
@EventHandler
public void onReceiveEvent(TelegramMessageReceiveEvent ev){
    TelegramBot bot = ev.getTelegramBot(); //메시지를 받은 봇
    Message msg = ev.getMessage(); //메시지 객체
}

@EventHandler
public void onReceiveEvent(TelegramCommandReceiveEvent ev){
    TelegramBot bot = ev.getTelegramBot(); //메시지를 받은 봇
    String command = ev.getCommand(); //실행한 커맨드
    String[] args = ev.getArgs();
    Message msg = ev.getMessage(); //메시지 객체
}
```