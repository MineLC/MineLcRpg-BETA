package Method;

import Objets.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class DragAndDrops {
    public static String prefix = "&8&l[&3&lMineLC &6&lNetwork&8&l]&e ";
    public DragAndDrops(String event, HumanEntity hentity, File folder, Inventory inventory,int slot, ItemStack item, ItemStack cursor, InventoryView view) {
        Player player = (Player) hentity;
        PlayerFlagObjetes flagObjetes = new PlayerFlagObjetes(folder, player);
        String dano = flagObjetes.getFlagDano();
        String itemnivel = flagObjetes.getFlagItemLevel();
        File file = flagObjetes.getFileFolder();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&eEsto no es compatible con el objeto"));
        ItemCreator espadamadera = new ItemCreator(Material.WOODEN_SWORD, "&c&lEspada antigua nivel " + itemnivel, "&e", "&8Objeto unico", "&e", "&eDaño :&4 " + dano, "&e", "&eEspada mejorable", "&eEste objeto no es tradeable", "&eTodas las mejoras son compatibles y acumulables");
        ItemCreator pergaminovida = new ItemCreator(Material.PAPER, "&c&lPergamino antiguo", "&e", "&8Objeto muy raro", "&e", "&eAumenta el daño de un objeto a &a+0.1", "&e", "&8______________________", "&2Click arriva de un objeto para imbuirlo", "&8______________________");
        ItemStack item1 = espadamadera.getItemStack();
        ItemStack item2 = pergaminovida.getItemStack();
        if (item.equals(item1) && cursor.equals(item2)) {
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
            config.set("Daño", danoformat);
            config.set("ItemNivel", itemnivelformat);
            try {
                config.save(file);
            } catch (IOException ignored) {
            }
            dano = config.getString("Daño");
            itemnivel = config.getString("ItemNivel");
            ItemCreator espadamadera1 = new ItemCreator(Material.WOODEN_SWORD, "&c&lEspada antigua nivel " + itemnivel, "&e", "&8Objeto unico", "&e", "&eDaño :&4 " + dano, "&e", "&eEspada mejorable", "&eEste objeto no es tradeable", "&eTodas las mejoras son compatibles y acumulables");
            ItemStack item3 = espadamadera1.getItemStack();
            view.setCursor(null);
            inventory.setItem(slot, item3);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&eTu arma ha subido de nivel " + itemnivelsuma + danosuma));
        }
    }
}
