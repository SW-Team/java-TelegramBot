package milk.telegram.type.reply;

public class ReplyKeyboardHide extends ReplyMarkup{

    private Boolean selective;

    public ReplyKeyboardHide(){
        this.put("hide_keyboard", true);
    }

    public Boolean getSelective(){
        return this.optBoolean("selective");
    }

    public void setSelective(boolean selective){
        this.put("selective", selective);
    }

}
