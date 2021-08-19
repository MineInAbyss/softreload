package com.mineinabyss.softreload;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.bukkit.plugin.Plugin;

public class SoftReloadServiceImpl implements SoftReloadService {

  private final Map<Plugin, Supplier<Boolean>> pluginToReloadFn;

  public SoftReloadServiceImpl() {
    this.pluginToReloadFn = new HashMap<>();
  }

  @Override
  public void register(Plugin plugin, Supplier<Boolean> reloadFn) {
    pluginToReloadFn.put(plugin, reloadFn);
  }

  Map<Plugin, Supplier<Boolean>> getPluginsToReload() {
    return ImmutableMap.copyOf(pluginToReloadFn);
  }
}
