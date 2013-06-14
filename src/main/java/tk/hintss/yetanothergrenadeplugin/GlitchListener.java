package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GlitchListener implements Listener {
    YetAnotherGrenadePlugin plugin;
    
    public GlitchListener(YetAnotherGrenadePlugin instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("yagp.throw.glitch") && plugin.getConfig().getBoolean("glitch.enabled")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getPlayer().getItemInHand().getTypeId() == plugin.getConfig().getInt("glitch.item")) {
                    if (plugin.getConfig().getBoolean("glitch.consumeitem")) {
                        event.getPlayer().getInventory().removeItem(new ItemStack(event.getPlayer().getItemInHand().getTypeId(), 1));
                        event.getPlayer().updateInventory();
                    }
                    Item grenade = event.getPlayer().getWorld().dropItem(event.getPlayer().getEyeLocation(), new ItemStack(plugin.getConfig().getInt("glitch.item")));
                    plugin.setGrenade(grenade);
                    grenade.setVelocity(event.getPlayer().getLocation().getDirection());
                    new MakeGlitch(plugin, grenade).runTaskLater(plugin, plugin.getConfig().getInt("glitch.implosiondelay"));
                    event.setCancelled(true);
                }
            }
        }
    }
}
