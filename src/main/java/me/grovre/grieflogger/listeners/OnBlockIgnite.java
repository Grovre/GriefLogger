package me.grovre.grieflogger.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class OnBlockIgnite implements Listener {

    @EventHandler
    public void OnBlockIgnition(BlockIgniteEvent event) {

        Player player;
        Entity igniter = event.getIgnitingEntity();
        Block block = event.getBlock();
        // If the igniter wasn't an entity, return
        if(igniter == null) {
            return;
        }
        // If the block type isn't a form of TNT, return
        if(block.getType() != Material.TNT && block.getType() != Material.TNT_MINECART) {
            return;
        }
        // No point in taking up a few bytes of memory if it's returned anyways, so checks if it needs to return first
        Location blockLoc = block.getLocation();
        int blockX = blockLoc.getBlockX();
        int blockY = blockLoc.getBlockY();
        int blockZ = blockLoc.getBlockZ();

        if(igniter instanceof Player) {
            player = (Player) igniter;
            System.out.println("Player " + player.getName() + " ignited TNT at " + blockX + ", " + blockY + ", " + blockZ + ") in " + blockLoc.getWorld());
            System.out.println("Player's UUID: " + player.getUniqueId());
            System.out.println("Player's current location: (" + player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ() + ") in " + player.getLocation().getWorld());
        } else {
            System.out.println(igniter.getType().name() + " ignited TNT at (" + blockX + ", " + blockY + ", " + blockZ + ") in " + blockLoc.getWorld());
        }
    }

}
