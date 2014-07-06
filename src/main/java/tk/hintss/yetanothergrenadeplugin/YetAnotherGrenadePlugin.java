package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

public class YetAnotherGrenadePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        
        if (this.getConfig().getBoolean("auto-update")) {
            Updater updater = new Updater(this, 58496, this.getFile(), Updater.UpdateType.DEFAULT, true);
        }

        if (getConfig().getBoolean("stats")) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
            } catch (IOException e) {
                getLogger().warning("stats no werk :(");
            }
        }
        
        getCommand("grenades").setExecutor(new GrenadesCommand(this));
        
        getServer().getPluginManager().registerEvents(new ProtectGrenades(this), this);
        
        getServer().getPluginManager().registerEvents(new GrenadeListener(this), this);
    }
    
    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }
    
    public void setGrenade(Metadatable item){
        item.setMetadata("isGrenade",new FixedMetadataValue(this,true));
    }
    
    public Boolean isGrenade(Metadatable item){
        List<MetadataValue> values = item.getMetadata("isGrenade");  
        for(MetadataValue value : values){
            if(value.getOwningPlugin().getDescription().getName().equals(this.getDescription().getName())){
                return value.asBoolean();
            }
        }
        return false;
    }
}
