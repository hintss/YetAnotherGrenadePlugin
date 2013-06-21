package tk.hintss.yetanothergrenadeplugin;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MakeSingularitySwarm extends BukkitRunnable {
	private YetAnotherGrenadePlugin plugin;
    private FallingBlock entity;
    private Location location;
    private int iterations = 0;
    
    public MakeSingularitySwarm(YetAnotherGrenadePlugin plugin, FallingBlock entity, Location location, int iterations) {
    	this.plugin = plugin;
        this.entity = entity;
        this.location = location;
        this.iterations = iterations;
    }
    
    public void run() {
        Random rand = new Random();
        location.getWorld().spawnFallingBlock(new Location(location.getWorld(), location.getX() - 0.5 + rand.nextDouble(), location.getY() - 0.5 + rand.nextDouble(), location.getZ() - 0.5 + rand.nextDouble()), entity.getMaterial(), entity.getBlockData());
        entity.setVelocity(new Vector(0, 0, 0));
        iterations--;
        if (iterations >= 0) {
        	new MakeSingularitySwarm(plugin, entity, location, iterations).runTaskLater(plugin, 1);
        }
    }
}
