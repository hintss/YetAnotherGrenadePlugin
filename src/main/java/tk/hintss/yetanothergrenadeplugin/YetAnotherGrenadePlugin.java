package tk.hintss.yetanothergrenadeplugin;

import java.io.IOException;
import java.util.List;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

public class YetAnotherGrenadePlugin extends JavaPlugin {
    public void onEnable() {
        this.saveDefaultConfig();
        
        if (this.getConfig().getBoolean("auto-update")) {
            Updater updater = new Updater(this, "yet-another-grenade-plugin", this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
        
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().warning("stats no werk :(");
        }
        
        getCommand("grenades").setExecutor(new GrenadesCommand(this));
        
        getServer().getPluginManager().registerEvents(new ProtectGrenades(this), this);
        
        getServer().getPluginManager().registerEvents(new GrenadeListener(this), this);
        getServer().getPluginManager().registerEvents(new IncendiaryListener(this), this);
        getServer().getPluginManager().registerEvents(new NerfListener(this), this);
        getServer().getPluginManager().registerEvents(new FragListener(this), this);
        getServer().getPluginManager().registerEvents(new GlitchListener(this), this);
        getServer().getPluginManager().registerEvents(new SingularityListener(this), this);
    }
    
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
