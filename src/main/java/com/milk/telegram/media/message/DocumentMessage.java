package com.milk.telegram.media.message;

import com.milk.telegram.media.message.type.Document;
import org.json.JSONObject;

public class DocumentMessage extends Message{

    private Document document;

    public DocumentMessage(JSONObject object){
        super(object);
        this.document = new Document(object.getJSONObject("document"));
    }

    public Document getDocument(){
        return this.document;
    }

}
