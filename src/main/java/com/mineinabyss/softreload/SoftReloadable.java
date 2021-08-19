package com.mineinabyss.softreload;

/**
 * Marks a plugin as soft-reloadable.
 */
public interface SoftReloadable {

  /**
   * Method to run when a soft reload is requested.
   *
   * @return true if the soft reload was successful. False otherwise.
   */
  boolean softReload();
}
