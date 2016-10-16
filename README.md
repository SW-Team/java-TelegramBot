# java-TelegramAPI
  
Author: **[SW-Team](https://github.com/SW-Team)**
  
###LICENSE
* org.json - JSON-LICENSE
  
##Usage
### initializing bot
```java
TelegramBot bot = new TelegramBot("Bot::TOKEN");
bot.setHandler(list -> list.forEach(update -> {
    Message message = update.getMessage();
    //...etc
}));
bot.start();
```
### send message
```java
(TextMessage)
TextMessage message = new TextMessageSender(bot).setText("Hello, World!").setChatId("@username").send();
```
### get data
```java
(Chat)
Chat chat = new ChatGetter(bot).getChatId("@username").send();
```