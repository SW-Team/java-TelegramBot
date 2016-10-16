package milk.telegram.type.file;

import milk.telegram.type.file.photo.PhotoSize;
import milk.telegram.type.Identifier;
import org.json.JSONObject;

public class Document implements Identifier<String>{

    private final String file_id;
    private final String file_name;

    private final String mime_type;

    private final PhotoSize thumb;

    private final Integer size;

    private Document(JSONObject object){
        this.file_id = object.getString("file_id");
        this.file_name = object.optString("file_name");
        this.mime_type = object.optString("mime_type");

        this.thumb = PhotoSize.create(object.optJSONObject("thumb"));
        this.size = object.has("file_size") ? object.getInt("file_size") : null;
    }

    public static Document create(JSONObject object){
        if(object == null){
            return null;
        }
        return new Document(object);
    }

    public String getId(){
        return file_id;
    }

    public PhotoSize getThumb(){
        return thumb;
    }

    public String getFileName(){
        return file_name;
    }

    public String getMimeType(){
        return mime_type;
    }

    public Integer getSize(){
        return size;
    }

}
