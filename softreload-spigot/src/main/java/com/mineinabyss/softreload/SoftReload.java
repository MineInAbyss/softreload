package com.mineinabyss.softreload;

import java.util.Objects;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoftReload extends JavaPlugin {

  @Override
  public void onEnable() {
    SoftReloadServiceImpl service = new SoftReloadServiceImpl();
    getServer().getServicesManager()
        .register(SoftReloadService.class, service, this, ServicePriority.Normal);
    Objects.requireNonNull(getCommand("softreload"))
        .setExecutor(new SoftReloadCommandExecutor(service));
  }

  @Override
  public void onDisable() {
    getServer().getServicesManager().unregisterAll(this);
  }
}
