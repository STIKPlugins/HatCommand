package me.stokmenn.hatcommand.commands;

import me.stokmenn.hatcommand.HatCommand;
import me.stokmenn.hatcommand.config.Config;
import me.stokmenn.hatcommand.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Hat implements CommandExecutor {
    private final HatCommand plugin;
    private final Messages messages;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public Hat(HatCommand plugin, Messages messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (args.length == 1 && args[0].equals("reload")) return reloadConfig(sender);

        if (!(sender instanceof Player player)) {
            sender.sendMessage(messages.get("notPlayer"));
            return false;
        } else if (!player.hasPermission("hat.use")) {
            sender.sendMessage(messages.get("noPermission"));
            return false;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getType() == Material.AIR) {
            player.sendMessage(messages.get("noItem"));
            return false;
        } else if (itemInHand.getAmount() != 1) {
            player.sendMessage(messages.get("needOneItem"));
            return false;
        } else if (Config.materialsEnabled && !Config.materials.contains(itemInHand.getType())) {
            player.sendMessage(messages.get("wrongMaterial"));
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long lastUsed = cooldowns.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastUsed < Config.cooldown) {
            double remaining = Math.max((Config.cooldown - (currentTime - lastUsed)) / 1000.0, 0.1);
            String formatted = String.format("%.1f", remaining);

            player.sendMessage(messages.get("cooldown", Map.of("%cooldown%", formatted)));
            return true;
        }

        ItemStack helmet = player.getInventory().getHelmet();

        player.getInventory().setHelmet(itemInHand);
        player.getInventory().setItemInMainHand(helmet);

        player.sendMessage(messages.get("successful"));
        cooldowns.put(player.getUniqueId(), currentTime);
        return true;
    }

    private boolean reloadConfig(CommandSender sender) {
        if (!sender.hasPermission("hat.reload")) {
            sender.sendMessage(messages.get("noPermissionToReload"));
            return false;
        }
        Bukkit.getAsyncScheduler().runNow(plugin, task -> {
            Config.reload();
            messages.reload();
        });

        sender.sendMessage(messages.get("configReloaded"));
        return true;
    }
}
