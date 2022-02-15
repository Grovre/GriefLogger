package me.grovre.grieflogger.listeners;

import me.grovre.grieflogger.GriefLogger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class OnTntIgnite implements Listener {

    @EventHandler
    public void OnBlockIgnition(BlockIgniteEvent event) {
        System.out.println("block ignite event");

        Entity igniter = event.getIgnitingEntity();
        Block block = event.getBlock();
        // If the igniter wasn't an entity, return
        if(igniter == null) {
            System.out.println("igniter is null");
            return;
        }
        // If the block type isn't a form of TNT, return
        if(block.getType() != Material.TNT && block.getType() != Material.TNT_MINECART) {
            System.out.println("ignited block not tnt");
            return;
        }
        // No point in taking up a few bytes of memory if it's returned anyways, so checks if it needs to return first
        int[] blockLoc = GriefLogger.getXYZfromLocation(block.getLocation());

        if(igniter instanceof Player) {
            System.out.println("logging bro");
            Player player = (Player) igniter;
            System.out.println("Player " + player.getName() + " ignited TNT at " + blockLoc[0] + ", " + blockLoc[1] + ", " + blockLoc[2] + ") in " + block.getLocation().getWorld());
            System.out.println("Player's UUID: " + player.getUniqueId());
            System.out.println("Player's current location: (" + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ() + ") in " + player.getLocation().getWorld());
        } else {
            System.out.println("igniter was not an instance of Player");
            System.out.println(igniter.getType().name() + " ignited TNT at (" + blockLoc[0] + ", " + blockLoc[1] + ", " + blockLoc[2] + ") in " + block.getLocation().getWorld());
        }
    }

}
