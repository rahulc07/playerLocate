package org.playerlocate.playerlocate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class locateplayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Make sure the command is being used right
        if (args.length == 0) {
            return false;
        }
        // Extract the player name from the arguments
        String playerName = args[0];
        // Get the bukkit identifier for the player based on their name
        Player target = Bukkit.getServer().getPlayerExact(playerName);

        // If the player is not online, grab their last seen position from logout (see logout class)
        if (target == null) {
            String yamlLocation = String.format("player.%s.location", playerName);
            // Check to see if the players logout coords have been cached
            if (!PlayerLocate.getLogout().contains(yamlLocation)) {
                sender.sendMessage("§cPlayer not found");
                return true;
            }
            // Otherwise get the player location from the yaml
            String msg = PlayerLocate.getLogout().getString(yamlLocation);
            sender.sendMessage(msg);
            return true;
        }

        // If the player is online, get their active position

        Location locationOfPlayer = target.getLocation();
        // Convert the bukkit specific location variable into something more usable
        double x = locationOfPlayer.getX();
        double y = locationOfPlayer.getY();
        double z = locationOfPlayer.getZ();

        // Check what dimension their in and convert it into something more friendly
        String dimension = target.getWorld().getEnvironment().toString();

        if (dimension.equals("NORMAL")) {
            dimension = "§aOverworld";
        }
        if (dimension.equals("NETHER")) {
            dimension = "§4Nether";
        }
        if (dimension.equals("THE_END")) {
            dimension = "§5End";
        }


        // Send the message to the person that ran the command, rounded to 2 decimal places
        String msg = String.format("Coordinates of %s are: %.2f %.2f %.2f in the %s", playerName, x, y, z, dimension);
        sender.sendMessage(msg);
        return true;
    }
}