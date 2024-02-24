package minelc.main;

import Method.PlayerJoinFlagAssignation;
import listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public final class MinelcRpg extends JavaPlugin {
    public String ruta;
    public static String prefix = "&8&l[&3&lMineLCRpg &6&lNetwork&8&l]&e ";
    @Override
    public void onEnable() {
        registerEvents();
        ConfigOptions();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+"El Plugin se está iniciando"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+"El Plugin se está cerrando"));
    }
    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
    public void ConfigOptions(){
        File config = new File(this.getDataFolder(),"config.yml");
        ruta = config.getPath();
        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }
}
