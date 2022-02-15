package me.grovre.grieflogger.listeners.spawner;

import me.grovre.grieflogger.GriefLogger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.IOException;

public class OnSpawnerPlace implements Listener {

    @EventHandler
    public void OnPlayerSpawnerPlace(BlockPlaceEvent event) throws IOException {

        Block spawner = event.getBlock();
        if(spawner.getType() != Material.SPAWNER) {
            return;
        }

        CreatureSpawner creatureSpawner = (CreatureSpawner) spawner.getState();
        Player owner = event.getPlayer();
        GriefLogger.setSpawnerOwner(creatureSpawner, owner.getUniqueId());
    }

}
