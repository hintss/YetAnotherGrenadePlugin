package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class MakeKaboom extends BukkitRunnable {
    private Entity grenade = null;
    private Location location = null;
    private float power;
    private boolean setFire;
    private boolean breakBlocks;
    
    public MakeKaboom(Entity grenade, float power, boolean setFire, boolean breakBlocks) {
        this.grenade = grenade;
        this.power = power;
        this.setFire = setFire;
        this.breakBlocks = breakBlocks;
    }
    
    public MakeKaboom(Item grenade, float power, boolean setFire, boolean breakBlocks) {
        this.grenade = grenade;
        this.power = power;
        this.setFire = setFire;
        this.breakBlocks = breakBlocks;
    }
    
    public MakeKaboom(Location location, float power, boolean setFire, boolean breakBlocks) {
        this.location = location;
        this.power = power;
        this.setFire = setFire;
        this.breakBlocks = breakBlocks;
    }
    
    public void run() {
        if (grenade != null) {
            grenade.getWorld().createExplosion(grenade.getLocation().getX(), grenade.getLocation().getY(), grenade.getLocation().getZ(), this.power, this.setFire, this.breakBlocks);
            grenade.remove();
        } else if (location != null) {
            location.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), this.power, this.setFire, this.breakBlocks);
        }
    }
}
