# SoftReload

A simple plugin that soft-reloads other plugins. Typically this is a simple config reload.

## How to use

Run `/softreload`. You need to have the permission node `softreload.softreload`.

## How to integrate a plugin

### Add dependency on SoftReload

//TODO after publishing

### Add soft dependency in plugin.yml

```yml
name: 'YourPlugin'
...
softdepend:
  - SoftReload
...
```

### Implement SoftReloadable

```java
class YourPlugin extends JavaPlugin {
  //...

  @Override
  public boolean onEnable() {
    //...
    registerSoftReload();
  }

  void registerSoftReload() {
    try {
      RegisteredServiceProvider<SoftReloadService> softReloadService = getServer().getServicesManager()
          .getRegistration(SoftReloadService.class);
      if (softReloadService != null) {
        softReloadService.getProvider().register(this, this::softReload);
      }
    } catch (NoClassDefFoundError ignored) {
    }
  }

  boolean softReload() {
    //... reload configs, etc.
    return true; // false if reload failed.
  }
}
```