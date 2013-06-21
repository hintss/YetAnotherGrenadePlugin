package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MakeSingularityStart extends BukkitRunnable {
    private final YetAnotherGrenadePlugin plugin;
    private final Entity grenade;
    private final Integer radius;
    
    public MakeSingularityStart(YetAnotherGrenadePlugin plugin, Entity grenade) {
        this.plugin = plugin;
        this.grenade = grenade;
        this.radius = plugin.getConfig().getInt("glitch.implosionradius");
    }
    
    public void run() {
        World world = grenade.getWorld();
        Location location = grenade.getLocation();
        
        grenade.remove();
        
        for (int x = grenade.getLocation().getBlockX() - radius; x <= grenade.getLocation().getBlockX() + radius; x++) {
            for (int y = grenade.getLocation().getBlockY() - radius; y <= grenade.getLocation().getBlockY() + radius; y++) {
                for (int z = grenade.getLocation().getBlockZ() - radius; z <= grenade.getLocation().getBlockZ() + radius; z++) {
                    Block block = grenade.getWorld().getBlockAt(x, y, z);
                    Material material = block.getType();
                    if (location.distance(block.getLocation()) <= plugin.getConfig().getInt("glitch.implosionradius")) {
                        if (!((material == Material.BEDROCK) && !plugin.getConfig().getBoolean("glitch.affectbedrock"))) {
                            if (material.isSolid()) {
                                block.setTypeId(0);
                                FallingBlock fallingblock = world.spawnFallingBlock(new Location(world, x, y + 0.5, z), material, block.getData());

                                new MakeSingularitySwarm(plugin, fallingblock, location, plugin.getConfig().getInt("glitch.explosiondelay")).runTaskLater(plugin, 1);
                            }
                        }
                    }
                }
            }
        }
        for (int boom = 0; boom < plugin.getConfig().getInt("glitch.explosioncount"); boom++) {
            new MakeKaboom(new Location(world, location.getX(), location.getY() - 1, location.getZ()), (float)plugin.getConfig().getInt("glitch.explosionpower"), false, true).runTaskLater(plugin, plugin.getConfig().getInt("glitch.explosiondelay"));
        }
    }
}
