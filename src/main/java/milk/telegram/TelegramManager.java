package milk.telegram;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class TelegramManager extends PluginBase{

    @Override
    public void onEnable(){
        this.getServer().getLogger().info(TextFormat.GOLD + "[TelegramManager]Plugin has been enabled");
    }

}