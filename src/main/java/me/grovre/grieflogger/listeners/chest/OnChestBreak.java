package me.grovre.grieflogger.listeners.chest;

import me.grovre.grieflogger.GriefLogger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;
import java.util.UUID;

public class OnChestBreak implements Listener {

    @EventHandler
    public void OnPlayerBreakChest(BlockBreakEvent event) throws IOException, ClassNotFoundException {

        if(event.getBlock().getType() != Material.CHEST) {
            return;
        }

        Chest chest = (Chest) event.getBlock().getState();
        UUID ownerUUID = GriefLogger.getChestOwner(chest);
        if(ownerUUID == null) {
            return;
        }
        OfflinePlayer owner = Bukkit.getOfflinePlayer(ownerUUID);
        Player chestBreaker = event.getPlayer();
        /*if(chestBreaker.getUniqueId().equals(ownerUUID)) {
            return;
        }*/
        System.out.println(chestBreaker.getName() + " is breaking " + owner.getName() + "'s chest at " + GriefLogger.getXYZasStringFromLocation(chest.getLocation()));
    }

}
