package milk.telegram.type.message;

import milk.telegram.type.Textable;
import milk.telegram.type.file.Document;
import org.json.JSONObject;

public class DocumentMessage extends Message implements Textable{

    private String caption;
    private Document document;

    public DocumentMessage(JSONObject object){
        super(object);
        this.caption = object.optString("caption", null);
        this.document = Document.create(object.getJSONObject("document"));
    }

    public String getText(){
        return this.caption;
    }

    public Document getDocument(){
        return this.document;
    }

    public String getName(){
        return "파일";
    }

}
