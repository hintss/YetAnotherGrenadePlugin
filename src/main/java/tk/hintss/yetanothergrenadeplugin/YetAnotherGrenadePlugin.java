package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class YetAnotherGrenadePlugin extends JavaPlugin {
    public HashMap<UUID, UUID> items = new HashMap<UUID, UUID>();

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
    
    public void setGrenade(Entity item, Player thrower) {
        items.put(item.getUniqueId(), thrower.getUniqueId());
    }
    
    public Boolean isGrenade(Entity item) {
        return items.containsKey(item.getUniqueId());
    }

    public Player getThrower(Entity grenade) {
        return getServer().getPlayer(items.get(grenade));
    }
}
