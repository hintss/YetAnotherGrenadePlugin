package tk.hintss.yetanothergrenadeplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GrenadesCommand implements CommandExecutor {
    private YetAnotherGrenadePlugin plugin;
    
    public GrenadesCommand(YetAnotherGrenadePlugin instance) {
        plugin = instance;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("grenades")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("yagp.reload") || !(sender instanceof Player)) {
                        plugin.reloadConfig();
                        sender.sendMessage(ChatColor.GREEN + "[YAGP] Config reloaded!");
                        plugin.getLogger().info(sender.getName() + " has reloaded the config!");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "[YAGP] Usage: /grenades reload");
            }
            return true;
        } else {
            return false;
        }
    }
}
