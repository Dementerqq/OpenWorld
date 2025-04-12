package my.dementerqq.openWorld.commands;

import my.dementerqq.openWorld.OpenWorld;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Command implements CommandExecutor {
    private final OpenWorld plugin;
    public Command(OpenWorld plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        FileConfiguration config = plugin.getPluginConfig();
        if (!sender.hasPermission("openworld.admin")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cOpenWorld&8] &7У вас нету права использовать данную команду!"));
            return true;
        }
        if (args.length != 0) {
            switch (args[0]) {
                case "reload":
                    plugin.loadConfigs();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aOpenWorld&8] &7перезагружен!"));
                    return true;
                case "open":
                    if (!args[1].isEmpty()) {
                        config.getConfigurationSection("worlds").set(args[1], true);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aOpenWorld&8] &7теперь &f" + args[1] + " &aоткрыт&7!"));
                        plugin.savePluginConfig();
                        plugin.loadConfigs();
                        return true;
                    }
                    break;
                case "close":
                    if (!args[1].isEmpty()) {
                        config.getConfigurationSection("worlds").set(args[1], false);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aOpenWorld&8] &7теперь &f" + args[1] + " &cзакрыт&7!"));
                        plugin.savePluginConfig();
                        plugin.loadConfigs();
                        return true;
                    }
                    break;
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aOpenWorld&8] &7Используй: &f/openworld <reload|open|close> <world>"));
                    return true;
            }
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aOpenWorld&8] &7Используй: &f/openworld <reload|open|close> <world>"));
        return true;
    }
}
