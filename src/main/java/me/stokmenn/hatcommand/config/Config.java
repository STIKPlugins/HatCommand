package me.stokmenn.hatcommand.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Config {
    private static JavaPlugin plugin;

    public static int cooldown;

    public static boolean materialsEnabled;
    public static final Set<Material> materials = new HashSet<>();

    public static void init(JavaPlugin plugin) {
        Config.plugin = plugin;
        plugin.saveDefaultConfig();
        reload();
    }

    public static void reload() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        cooldown = config.getInt("cooldown", 10000);
        materialsEnabled = config.getBoolean("materialsEnabled", false);

        materials.clear();
        for (String materialName : config.getStringList("materials")) {
            Material material = Material.getMaterial(materialName);
            if (material != null) {
                materials.add(material);
            }
        }
    }
}