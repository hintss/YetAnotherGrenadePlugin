package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ProtectGrenades implements Listener {
    YetAnotherGrenadePlugin plugin;
    
    public ProtectGrenades(YetAnotherGrenadePlugin instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (plugin.isGrenade(event.getItem())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBurn(EntityCombustEvent event) {
        if (plugin.isGrenade(event.getEntity())) {
            event.setCancelled(true);
        }
    }
}
