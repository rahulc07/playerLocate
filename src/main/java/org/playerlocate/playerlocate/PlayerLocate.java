package org.playerlocate.playerlocate;


import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public class PlayerLocate extends JavaPlugin {

    private static File customConfigFile;
    private static FileConfiguration customConfig;
    @Override
    public void onEnable() {
        getLogger().info("Locator has been Enabled");
        this.getCommand("locateplayer").setExecutor(new locateplayer());
        getServer().getPluginManager().registerEvents(new logout(), this);
        createCustomConfig();
        getLogout().options().copyDefaults(true);
        save();

            }
    @Override
    public void onDisable() {
        getLogger().info("Locator Shutting Down");
    }
    // Even I barely understand what is going on after this, this just initializes the custom yaml, don't touch
    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "logout-location.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("logout-location.yml", false);

        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static FileConfiguration getLogout() {
        return customConfig;
    }
    public static void save() {

        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            ;

        }
    }




}