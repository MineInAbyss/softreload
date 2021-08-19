package com.mineinabyss.softreload;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Command executor for performing a soft reload.
 */
public class SoftReloadCommandExecutor implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
      if (plugin instanceof SoftReloadable) {
        boolean success;
        try {
          success = ((SoftReloadable) plugin).softReload();
        } catch (RuntimeException e) {
          String errorMessage = String.format(
              "Plugin %s threw an exception when attempting to soft reload!", plugin.getName());
          sender.sendMessage(ChatColor.DARK_RED + errorMessage);

          plugin.getLogger().warning(errorMessage);
          plugin.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
          continue;
        }
        if (!success) {
          sender.sendMessage(ChatColor.DARK_RED + String.format("Plugin %s failed to soft reload!",
              plugin.getName()));
        }
      }
    }

    return true;
  }


}
