package my.dementerqq.openWorld;

import my.dementerqq.openWorld.commands.CMDTabCompleter;
import my.dementerqq.openWorld.commands.Command;
import my.dementerqq.openWorld.utils.ColorConfig;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public final class OpenWorld extends JavaPlugin {
    public FileConfiguration messages;
    public FileConfiguration config;
    private File configFile;
    private File messagesFile;

    @Override
    public void onEnable() {
        getLogger().info("OpenWorld plugin enabled!");
        loadConfigs();
        getCommand("openworld").setExecutor(new Command(this));
        getCommand("openworld").setTabCompleter(new CMDTabCompleter());
        getServer().getPluginManager().registerEvents(new Events(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("OpenWorld plugin disabled!");
    }

    public void loadConfigs() {
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getPluginMessages() {
        return messages;
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }

    public void savePluginConfig() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String color(String key){
        return ColorConfig.getMessage(messages, key);
    }

}
