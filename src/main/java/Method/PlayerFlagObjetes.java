package Method;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerFlagObjetes {
    private String flagDano;
    private String flagItemLevel;
    private File fileFolder;
    public PlayerFlagObjetes(File folder, Player player){
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
            } catch (IOException e) {
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String dano = config.getString("Daño");
        String vida = config.getString("Vida");
        String xp = config.getString("Xp");
        String nivel = config.getString("Nivel");
        String itemnivel = config.getString("ItemNivel");
        this.flagDano = dano;
        this.flagItemLevel = itemnivel;
        this.fileFolder = file;
    }
    public String  getFlagDano(){
        return flagDano;
    }
    public String getFlagItemLevel(){
        return flagItemLevel;
    }
    public File getFileFolder(){
        return fileFolder;
    }
}
