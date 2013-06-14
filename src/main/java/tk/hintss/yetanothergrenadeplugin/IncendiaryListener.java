package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class IncendiaryListener implements Listener {
    YetAnotherGrenadePlugin plugin;
    
    public IncendiaryListener(YetAnotherGrenadePlugin instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("yagp.throw.incendiary") && plugin.getConfig().getBoolean("incendiary.enabled")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getTypeId() == plugin.getConfig().getInt("incendiary.item")) {
                    if (plugin.getConfig().getBoolean("incendiary.consumeitem")) {
                        event.getPlayer().getInventory().removeItem(new ItemStack(event.getPlayer().getItemInHand().getTypeId(), 1));
                        event.getPlayer().updateInventory();
                    }
                    Item grenade = event.getPlayer().getWorld().dropItem(event.getPlayer().getEyeLocation(), new ItemStack(plugin.getConfig().getInt("incendiary.item")));
                    plugin.setGrenade(grenade);
                    grenade.setVelocity(event.getPlayer().getLocation().getDirection());
                    new MakeKaboom(grenade, (float)plugin.getConfig().getInt("incendiary.explosionpower"), true, true).runTaskLater(plugin, plugin.getConfig().getInt("incendiary.explosiondelay"));
                    event.setCancelled(true);
                }
            }
        }
    }
}
