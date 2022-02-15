package me.grovre.grieflogger.listeners.chest;

import me.grovre.grieflogger.GriefLogger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.IOException;

public class OnChestPlace implements Listener {

    @EventHandler
    public void OnBlockPlaceEvent(BlockPlaceEvent event) throws IOException {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if(block.getType() != Material.CHEST && block.getType() != Material.CHEST_MINECART) {
            return;
        }
        Chest chest = (Chest) event.getBlockPlaced().getState();

        GriefLogger.setChestOwner(chest, player.getUniqueId());
    }

}
