# java-TelegramAPI
  
Author: **[SW-Team](https://github.com/SW-Team)**
  
###LICENSE
* org.json - JSON-LICENSE
  
##Usage
### Initializing & Start Bot
```java
TelegramBot bot = new TelegramBot("Bot::TOKEN");
bot.setHandler(list -> list.forEach(update -> {
    Message message = update.getMessage();
    //...etc
}));
bot.start();
```
### Send Message
```java
TextMessage message = new TextMessageSender(bot).setText("Hello, World!").setChatId("@username").send();
```
### Get Data
```java
Chat chat = new ChatGetter(bot).getChatId("@username").send();
```