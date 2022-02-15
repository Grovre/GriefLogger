package me.grovre.grieflogger;

import me.grovre.grieflogger.listeners.OnTntIgnite;
import me.grovre.grieflogger.listeners.chest.OnChestInteract;
import me.grovre.grieflogger.listeners.chest.OnChestPlace;
import me.grovre.grieflogger.listeners.chest.OnDoubleChestInteract;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.DoubleChest;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.UUID;

public final class GriefLogger extends JavaPlugin {

    public static GriefLogger plugin;
    public static NamespacedKey chestOwnerKey;
    public static NamespacedKey spawnerOwnerKey;

    public static GriefLogger getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        plugin = this;
        keyInit();

        getServer().getPluginManager().registerEvents(new OnTntIgnite(), this);
        getServer().getPluginManager().registerEvents(new OnChestInteract(), this);
        getServer().getPluginManager().registerEvents(new OnDoubleChestInteract(), this);
        getServer().getPluginManager().registerEvents(new OnChestPlace(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void keyInit() {
        chestOwnerKey = new NamespacedKey(this, "ChestOwner");
        spawnerOwnerKey = new NamespacedKey(this, "SpawnerOwner");
    }

    public static UUID getChestOwner(Chest chest) throws IOException, ClassNotFoundException {
        PersistentDataContainer chestPDC = chest.getPersistentDataContainer();
        String encodedOwner = chestPDC.get(chestOwnerKey, PersistentDataType.STRING);
        if(encodedOwner == null) {
            return null;
        }
        return UuidCoder.decodeFromBase64(encodedOwner);
    }

    public static void setChestOwner(Chest chest, UUID uuid) throws IOException {
        PersistentDataContainer chestPDC = chest.getPersistentDataContainer();
        String encodedOwner = UuidCoder.encodeToBase64(uuid);
        chestPDC.set(chestOwnerKey, PersistentDataType.STRING, encodedOwner);
        chest.update();
    }

    public static UUID getChestOwner(DoubleChest chest) throws IOException, ClassNotFoundException {
        return getChestOwner((Chest) chest.getLeftSide());
    }

    public static void setChestOwner(DoubleChest chest, UUID uuid) throws IOException {
        setChestOwner((Chest) chest.getLeftSide(), uuid);
        setChestOwner((Chest) chest.getRightSide(), uuid);
    }

    public static UUID getSpawnerOwner(CreatureSpawner creatureSpawner) throws IOException, ClassNotFoundException {
        PersistentDataContainer csPDC = creatureSpawner.getPersistentDataContainer();
        String encodedOwner = csPDC.get(spawnerOwnerKey, PersistentDataType.STRING);
        if(encodedOwner == null) {
            return null;
        }
        return UuidCoder.decodeFromBase64(encodedOwner);
    }

    public static void setSpawnerOwner(CreatureSpawner creatureSpawner, UUID uuid) throws IOException {
        PersistentDataContainer csPDC = creatureSpawner.getPersistentDataContainer();
        String encodedOwner = UuidCoder.encodeToBase64(uuid);
        csPDC.set(spawnerOwnerKey, PersistentDataType.STRING, encodedOwner);
        creatureSpawner.update();
    }

    public static int[] getXYZfromLocation(Location loc) {
        return new int[]{loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()};
    }

    public static String getXYZasStringFromLocation(Location loc) {
        int[] coords = getXYZfromLocation(loc);
        return "(" + coords[0] + ", " + coords[1] + ", " + coords[2] + ")";
    }
}
