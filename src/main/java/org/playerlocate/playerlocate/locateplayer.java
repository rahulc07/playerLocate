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
        if (args.length == 0) {
            return false;
        }
        String playerName = args[0];
        Player target = Bukkit.getServer().getPlayerExact(playerName);


        Location locationOfPlayer = target.getLocation();
        double x = locationOfPlayer.getX();
        double y = locationOfPlayer.getY();
        double z = locationOfPlayer.getZ();
        String msg = String.format("Coords are: %.2f %.2f %.2f", x, y, z);
        Bukkit.broadcastMessage (msg);
        return true;
    }
}