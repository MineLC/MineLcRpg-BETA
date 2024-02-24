package listener;

import Method.PlayerFlagObjetes;
import Method.PlayerJoinFlagAssignation;
import Objets.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class PlayerListener implements Listener {
    public static String prefix = "&8&l[&3&lMineLC &6&lNetwork&8&l]&e ";
    private File getDataFolder;

    @EventHandler
    public void onInventory(InventoryClickEvent event){
        HumanEntity entity = event.getWhoClicked();
        Player player = (Player) entity;
        PlayerFlagObjetes flagObjetes = new PlayerFlagObjetes(this.getDataFolder,player);
        String dano = flagObjetes.getFlagDano();
        String itemnivel = flagObjetes.getFlagItemLevel();
        File file = flagObjetes.getFileFolder();
        Inventory inventory = event.getClickedInventory();
        int slot = event.getSlot();
        ItemStack item = event.getCurrentItem();
        ItemStack cursor = event.getCursor();
        ItemCreator espadamadera = new ItemCreator(Material.WOODEN_SWORD, "&c&lEspada antigua nivel "+itemnivel,"&e","&8Objeto unico","&e","&eDaño :&4 "+dano,"&e","&eEspada mejorable","&eEste objeto no es tradeable","&eTodas las mejoras son compatibles y acumulables");
        ItemCreator pergaminovida = new ItemCreator(Material.PAPER, "&c&lPergamino antiguo","&e","&8Objeto muy raro","&e","&eAumenta el daño de un objeto a &a+0.1","&e","&8______________________","&2Click arriva de un objeto para imbuirlo","&8______________________");
        InventoryView view = event.getView();
        ItemStack item1 = espadamadera.getItemStack();
        ItemStack item2 = pergaminovida.getItemStack();
        if (inventory != null && item != null && cursor != null && !event.isShiftClick() && event.isLeftClick()) {
            if (item.equals(item1) && cursor.equals(item2)) {
                //#########################    [ Items ]     ####################################
                assert dano != null;
                double danosuma = Double.parseDouble(dano);
                assert itemnivel != null;
                double itemnivelsuma = Double.parseDouble(itemnivel);
                double dano1 = danosuma + 0.1;
                double itemnivelsuma1 = itemnivelsuma + 1;
                double danoformat = Math.round(dano1 * 10.0) / 10.0;
                DecimalFormat d1 = new DecimalFormat("#0");
                String itemnivelformat = d1.format(itemnivelsuma1);
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("Daño",danoformat);
                config.set("ItemNivel", itemnivelformat);
                try {
                    config.save(file);
                } catch (IOException ignored) {
                }
                dano = config.getString("Daño");
                itemnivel = config.getString("ItemNivel");
                ItemCreator espadamadera1 = new ItemCreator(Material.WOODEN_SWORD, "&c&lEspada antigua nivel "+itemnivel,"&e","&8Objeto unico","&e","&eDaño :&4 "+dano,"&e","&eEspada mejorable","&eEste objeto no es tradeable","&eTodas las mejoras son compatibles y acumulables");
                ItemStack item3 = espadamadera1.getItemStack();
                event.setCancelled(true);
                view.setCursor(null);
                inventory.setItem(slot, item3);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&eTu arma ha subido de nivel "+itemnivelsuma+danosuma));
            }
        }
    }

    private File getDataFolder() {

        return getDataFolder;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerJoinFlagAssignation assignation = new PlayerJoinFlagAssignation(this.getDataFolder,player);
    }
}
