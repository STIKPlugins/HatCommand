package me.stokmenn.hatcommand;

import me.stokmenn.hatcommand.commands.Hat;
import me.stokmenn.hatcommand.config.Config;
import me.stokmenn.hatcommand.config.Messages;
import org.bukkit.plugin.java.JavaPlugin;

public final class HatCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        Config.init(this);
        Messages messages = new Messages(this);

        getCommand("hat").setExecutor(new Hat(this, messages));
    }
}
