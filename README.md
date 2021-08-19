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
class YourPlugin extends JavaPlugin implements SoftReloadable {
  //...

  @Override
  public boolean softReload() {
    //...          // Reload your config, etc.
    return true; // return false if reload fails for some reason.
  }
}
```