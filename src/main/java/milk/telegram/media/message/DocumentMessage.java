package milk.telegram.media.message;

import milk.telegram.media.interfaces.Textable;
import milk.telegram.media.message.type.Document;
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

}
