package me.grovre.grieflogger.listeners.chest;

import me.grovre.grieflogger.GriefLogger;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;
import java.util.UUID;

public class OnDoubleChestInteract implements Listener {

    @EventHandler
    public void OnPlayerChestInteract(InventoryClickEvent event) throws IOException, ClassNotFoundException {

        // Tries to get the chest being interacted with. If it's not a single chest, stops
        DoubleChest chest;
        try {
            chest = (DoubleChest) event.getInventory().getHolder();
            if(chest == null) {
                throw new NullPointerException();
            }
        } catch (ClassCastException e) {
            return;
        } catch (NullPointerException npe) {
            System.out.println("Chest is null?");
            npe.printStackTrace();
            return;
        }

        Player interactingPlayer = (Player) event.getWhoClicked();
        UUID chestOwnerUUID = GriefLogger.getChestOwner(chest);
        if(chestOwnerUUID == null) {
            GriefLogger.setChestOwner(chest, interactingPlayer.getUniqueId());
        }
        assert chestOwnerUUID != null;
        OfflinePlayer chestOwner = Bukkit.getOfflinePlayer(chestOwnerUUID);
        if(interactingPlayer.getUniqueId().equals(chestOwnerUUID)) {
            return;
        }

        System.out.println(interactingPlayer.getName() + " is tinkering with " + chestOwner.getName() + "'s chest at " + GriefLogger.getXYZasStringFromLocation(chest.getLocation()));
    }
}
