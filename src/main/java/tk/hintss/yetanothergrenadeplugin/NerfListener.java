package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NerfListener implements Listener {
    YetAnotherGrenadePlugin plugin;
    
    public NerfListener(YetAnotherGrenadePlugin instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("yagp.throw.nerf") && plugin.getConfig().getBoolean("nerf.enabled")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getTypeId() == plugin.getConfig().getInt("nerf.item")) {
                    if (plugin.getConfig().getBoolean("nerf.consumeitem")) {
                        event.getPlayer().getInventory().removeItem(new ItemStack(event.getPlayer().getItemInHand().getTypeId(), 1));
                        event.getPlayer().updateInventory();
                    }
                    Item grenade = event.getPlayer().getWorld().dropItem(event.getPlayer().getEyeLocation(), new ItemStack(plugin.getConfig().getInt("nerf.item")));
                    plugin.setGrenade(grenade);
                    grenade.setVelocity(event.getPlayer().getLocation().getDirection());
                    new MakeKaboom(grenade, (float)plugin.getConfig().getInt("nerf.explosionpower"), false, false).runTaskLater(plugin, plugin.getConfig().getInt("nerf.explosiondelay"));
                    event.setCancelled(true);
                }
            }
        }
    }
}
