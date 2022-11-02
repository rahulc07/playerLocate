package org.playerlocate.playerlocate;


import org.bukkit.plugin.java.JavaPlugin;





public class PlayerLocate extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Locator has been Enabled");
        this.getCommand("locateplayer").setExecutor(new locateplayer());
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}