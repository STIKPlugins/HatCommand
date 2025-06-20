package me.stokmenn.hatcommand.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Messages {
    private final File configFile;
    private YamlConfiguration config;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final Map<String, Component> messages = new HashMap<>();

    public Messages(JavaPlugin plugin) {
        this.configFile = new File(plugin.getDataFolder(), "messages.yml");
        plugin.saveResource("messages.yml", false);
        reload();
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(configFile);
        messages.clear();

        initMessage("notPlayer", "<red>✘ Only players can use this command!");
        initMessage("noPermission", "<red>✘ You don't have permission!");
        initMessage("noItem", "<red>✘ You're not holding any item!");
        initMessage("needOneItem", "<red>✘ You must hold exactly 1 item!");
        initMessage("wrongMaterial", "<red>✘ <white>You can't use this material!");
        initMessage("cooldown", "<red>✘ <white>You must wait %cooldown%s before using this command again!");
        initMessage("successful", "<green>✔ <white>You placed the item on your head!");

        initMessage("noPermissionToReload", "<red>✘ You don't have permission!");
        initMessage("configReloaded", "<green>✔ <white>Config reloaded!");

    }

    private void initMessage(String path, String def) {
        messages.put(path, miniMessage.deserialize(config.getString(path, def)));
    }

    public Component get(String key) {
        return messages.get(key);
    }

    public Component get(String path, Map<String, Object> placeholders) {
        Component message = messages.get(path);

        for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
            message = message.replaceText(builder ->
                    builder.match(entry.getKey())
                            .replacement(Component.text(entry.getValue().toString()))
            );
        }

        return message;
    }
}