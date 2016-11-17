package milk.telegram.method.getter;

import milk.telegram.type.user.User;
import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.photo.UserProfilePhotos;

public class UserProfilePhotosGetter extends Getter{

    public UserProfilePhotosGetter(TelegramBot bot){
        super(bot);
    }

    public UserProfilePhotosGetter setLimit(Number limit){
        this.put("limit", limit.longValue());
        return this;
    }

    public UserProfilePhotosGetter setOffset(Number offset){
        this.put("offset", offset.longValue());
        return this;
    }

    public UserProfilePhotosGetter setUserId(Object user_id){
        if(user_id instanceof User){
            this.put("user_id", ((User) user_id).getId());
        }else if(user_id instanceof Number){
            this.put("user_id", ((Number) user_id).intValue());
        }
        return this;
    }

    public UserProfilePhotos send(){
        return UserProfilePhotos.create(bot.updateResponse("getUserProfilePhotos", this));
    }
}
