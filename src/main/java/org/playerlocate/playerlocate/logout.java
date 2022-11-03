package org.playerlocate.playerlocate;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
public class logout implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        String playerName = player.getName();

        // Same convert dimension code from the command file
        String dimension = player.getWorld().getEnvironment().toString();
        if (dimension.equals("NORMAL")) {
            dimension = "§aOverworld";
        }
        if (dimension.equals("NETHER")) {
            dimension = "§4Nether";
        }
        if (dimension.equals("THE_END")) {
            dimension = "§5End";
        }
        // Take the location variables and make them into something more printable (location is not a string)
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        // Save the location on logout to the yaml under their own player name
        String saveLocation = String.format("%s was last seen at: %.2f %.2f %.2f in the %s", playerName, x, y, z, dimension);
        String yamlLocation = String.format("player.%s.location", playerName);
        PlayerLocate.getLogout().set(yamlLocation, saveLocation);
        // Save the yaml once this happens, helps if the server crashes
        PlayerLocate.save();


    }

}
