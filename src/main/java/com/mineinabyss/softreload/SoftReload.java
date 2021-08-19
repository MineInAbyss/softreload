package com.mineinabyss.softreload;

import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoftReload extends JavaPlugin {

  @Override
  public void onEnable() {
    Objects.requireNonNull(getCommand("softreload")).setExecutor(new SoftReloadCommandExecutor());
  }
}
