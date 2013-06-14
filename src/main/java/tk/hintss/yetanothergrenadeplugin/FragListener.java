package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FragListener implements Listener {
    YetAnotherGrenadePlugin plugin;
    
    public FragListener(YetAnotherGrenadePlugin instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("yagp.throw.frag") && plugin.getConfig().getBoolean("frag.enabled")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getTypeId() == plugin.getConfig().getInt("frag.item")) {
                    if (plugin.getConfig().getBoolean("frag.consumeitem")) {
                        event.getPlayer().getInventory().removeItem(new ItemStack(event.getPlayer().getItemInHand().getTypeId(), 1));
                        event.getPlayer().updateInventory();
                    }
                    Item grenade = event.getPlayer().getWorld().dropItem(event.getPlayer().getEyeLocation(), new ItemStack(plugin.getConfig().getInt("frag.item")));
                    plugin.setGrenade(grenade);
                    grenade.setVelocity(event.getPlayer().getLocation().getDirection());
                    new MakeEntitySpam(EntityType.ARROW, grenade, plugin.getConfig().getInt("frag.arrowcount"), true).runTaskLater(plugin, plugin.getConfig().getInt("frag.explosiondelay")-1);
                    new MakeKaboom(grenade, (float)plugin.getConfig().getInt("frag.explosionpower"), false, false).runTaskLater(plugin, plugin.getConfig().getInt("frag.explosiondelay"));
                    event.setCancelled(true);
                }
            }
        }
    }
}
