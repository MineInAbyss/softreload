package com.mineinabyss.softreload;

import java.util.function.Supplier;
import org.bukkit.plugin.Plugin;

/**
 * Service for setting up plugins soft reload.
 */
public interface SoftReloadService {


  /**
   * Register a function to reload a plugin.
   *
   * @param plugin   plugin to reload
   * @param reloadFn reloading function
   */
  void register(Plugin plugin, Supplier<Boolean> reloadFn);

}
