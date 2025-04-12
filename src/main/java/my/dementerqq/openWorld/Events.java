package my.dementerqq.openWorld;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Events implements Listener {

    private final OpenWorld plugin;
    public Events(OpenWorld plugin) { this.plugin = plugin; }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        String world = event.getTo().getWorld().getName();
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getPluginConfig();
        FileConfiguration messages = plugin.getPluginMessages();
        ConfigurationSection worlds = config.getConfigurationSection("worlds");
        assert worlds != null;

        if(worlds.isSet(world)) {
            if(!worlds.getBoolean(world)){
                event.setCancelled(true);
                player.sendTitle(plugin.color("title"), plugin.color("subtitle"));
            }
        }
    }

}
