package me.grovre.grieflogger.listeners.spawner;

import me.grovre.grieflogger.GriefLogger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;
import java.util.UUID;

public class OnSpawnerBreak implements Listener {

    @EventHandler
    public void OnPlayerBreakSpawner(BlockBreakEvent event) throws IOException, ClassNotFoundException {

        Block blockBroken = event.getBlock();
        if(blockBroken.getType() != Material.SPAWNER) {
            return;
        }
        Player breaker = event.getPlayer();
        CreatureSpawner creatureSpawner = (CreatureSpawner) blockBroken.getState();
        UUID ownerUUID = GriefLogger.getSpawnerOwner(creatureSpawner);
        assert ownerUUID != null;
        OfflinePlayer owner = Bukkit.getOfflinePlayer(ownerUUID);
        if(ownerUUID.equals(breaker.getUniqueId())) {
            return;
        }
        System.out.println(breaker.getName() + " is breaking " + owner.getName() + "'s spawner at " + GriefLogger.getXYZasStringFromLocation(blockBroken.getLocation()));
    }

}
