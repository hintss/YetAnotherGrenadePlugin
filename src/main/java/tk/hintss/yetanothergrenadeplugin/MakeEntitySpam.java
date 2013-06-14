package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MakeEntitySpam extends BukkitRunnable {
    private final EntityType type;
    private final Entity grenade;
    private final Integer count;
    private final Boolean shoot;
    
    public MakeEntitySpam(EntityType type, Entity grenade, Integer count, Boolean shoot) {
        this.type = type;
        this.grenade = grenade;
        this.count = count;
        this.shoot = shoot;
    }
    
    public void run() {
        for(int x = 0; x < this.count; x++) {
            if (type == EntityType.ARROW) {
                grenade.getWorld().spawnArrow(grenade.getLocation(), Vector.getRandom(), 0.6F, 120F);
            } else {
                Entity entity = grenade.getWorld().spawnEntity(grenade.getLocation(), type);
                if (shoot) {
                    entity.setVelocity(Vector.getRandom());
                }
            }
        }
    }
}
