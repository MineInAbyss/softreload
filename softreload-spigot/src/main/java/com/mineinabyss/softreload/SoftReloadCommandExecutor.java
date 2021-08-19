package com.mineinabyss.softreload;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Command executor for performing a soft reload.
 */
public class SoftReloadCommandExecutor implements CommandExecutor {

  private final SoftReloadServiceImpl service;

  public SoftReloadCommandExecutor(SoftReloadServiceImpl service) {
    this.service = service;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Builder<Plugin, Boolean> reloadStatus = ImmutableMap.builder();

    Map<Plugin, Supplier<Boolean>> pluginsToReload = service.getPluginsToReload();
    for (Plugin plugin : pluginsToReload.keySet()) {
      Supplier<Boolean> reloadFn = pluginsToReload.get(plugin);
      try {
        reloadStatus.put(plugin, reloadFn.get());
      } catch (RuntimeException e) {
        String errorMessage = String.format(
            "Plugin %s threw an exception when attempting to soft reload!", plugin.getName());
        sender.sendMessage(ChatColor.DARK_RED + errorMessage);

        plugin.getLogger().warning(errorMessage);
        plugin.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
      }
    }

    String statusString = reloadStatus
        .build()
        .entrySet()
        .stream()
        .map(entry -> (entry.getValue() ? ChatColor.GREEN
            : ChatColor.RED) + entry.getKey().getName() + ChatColor.RESET
        ).collect(Collectors.joining(", "));

    sender.sendMessage("Soft reload complete. Statuses: [" + statusString + "]");

    return true;
  }


}
