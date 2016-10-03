# TelegramManager
  
Author: **[SW-Team](https://github.com/SW-Team)**
  
###LICENSE
* org.json - JSON-LICENSE
  
##Usage
```java
TelegramBot bot = new TelegramBot("Bot::TOKEN");
bot.setHandler(list -> list.forEach(update -> {
    //NOW YOU USE IT!
}));
bot.start();
```