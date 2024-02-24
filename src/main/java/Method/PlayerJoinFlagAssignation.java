package Method;

import minelc.main.MinelcRpg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
public class PlayerJoinFlagAssignation {

    public PlayerJoinFlagAssignation(File folder, Player player){
        String prefix = "&8&l[&3&lMineLCRpg &6&lNetwork&8&l]&e ";
        UUID uuid = player.getUniqueId();
        String playerYml = uuid + ".yml";
        File PlayerFlag = new File(folder, "PlayerFlag");
        if (!PlayerFlag.exists()) {
            PlayerFlag.mkdirs();
        }
        File file = new File(PlayerFlag, playerYml);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                if (newFile){
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+"&eEl jugador no tiene flags. creando flag del jugador"));
                }
            } catch (IOException ignored) {
            }
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("Daño", "0.1");
            config.set("Vida", "2");
            config.set("Nivel", "0");
            config.set("ItemNivel", "0");
            config.set("Xp", "0");
            try {
                config.save(file);
            } catch (IOException ignored) {
            }
        }
        else {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            if (!config.contains("Daño")) {
                config.set("Daño", "0.1");
            }
            if (!config.contains("Vida")) {
                config.set("Vida", "2");
            }
            if (!config.contains("Nivel")) {
                config.set("Nivel", "0");
            }
            if (!config.contains("ItemNivel")) {
                config.set("ItemNivel", "0");
            }
            if (!config.contains("Xp")) {
                config.set("Xp", "0");
            }
            try {
                config.save(file);
            } catch (IOException ignored) {
            }
        }
    }
}
