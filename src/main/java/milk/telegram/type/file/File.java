package milk.telegram.type.file;

import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class File implements Identifier<String>{

    private final String file_id;
    private final String file_path;

    private final Integer file_size;

    private File(JSONObject object){
        this.file_id = object.getString("file_id");
        this.file_path = object.optString("file_path");
        this.file_size = object.has("file_size") ? object.getInt("file_size") : null;
    }

    public static File create(JSONObject object){
        if(object == null){
            return null;
        }
        return new File(object);
    }

    public String getId(){
        return file_id;
    }

    public String getPath(){
        return file_path;
    }

    public Integer getSize(){
        return file_size;
    }
}
