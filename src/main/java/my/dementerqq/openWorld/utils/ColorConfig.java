package my.dementerqq.openWorld.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ColorConfig {
    public static String getMessage(FileConfiguration config, String key) {
        String message = config.getString(key);
        assert message != null;
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
