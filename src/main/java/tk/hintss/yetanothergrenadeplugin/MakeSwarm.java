package tk.hintss.yetanothergrenadeplugin;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MakeSwarm extends BukkitRunnable {
    private Entity entity;
    private Location location;
    
    public MakeSwarm(Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
    }
    
    public void run() {
        Random rand = new Random();
        entity.teleport(new Location(location.getWorld(), location.getX() - 0.5 + rand.nextDouble(), location.getY() - 0.5 + rand.nextDouble(), location.getZ() - 0.5 + rand.nextDouble()));
        entity.setVelocity(new Vector(0, 0, 0));
    }
}
