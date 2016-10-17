package milk.telegram.method.getter;

import milk.telegram.bot.TelegramBot;
import milk.telegram.type.file.File;
import org.json.JSONObject;

public class FileGetter extends Getter{

    protected String file_id;

    public FileGetter(TelegramBot bot){
        super(bot);
    }

    public String getFileId(){
        return file_id;
    }

    public FileGetter setFileId(String file_id){
        this.file_id = file_id;
        return this;
    }

    public File send(){
        JSONObject object = new JSONObject();
        object.put("file_id", file_id);
        return File.create(bot.updateResponse("getFile", object));
    }

}
