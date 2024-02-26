package listener;

import Method.DragAndDrops;
import Method.PlayerJoinFlagAssignation;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class PlayerListener implements Listener {
    public static String prefix = "&8&l[&3&lMineLC &6&lNetwork&8&l]&e ";
    private File getDataFolder;

    @EventHandler
    public void onInventory(InventoryClickEvent event){
        if(event.getCurrentItem() == null) return;
        if(event.getCursor() == null) return;
        if(event.getCursor().getType() != Material.PAPER) return;
        if(event.getClickedInventory()  == null) return; // * NullClick (Ejecuta return si el evento es invocado cuando un jugador dropea desde el inventario)
        if(event.getClickedInventory().getType() != InventoryType.PLAYER) return; // * NullInventory (Ejecuta return si el inventario es tipo cofre,shulker,etc expto el tipo jugador)
        if(!(event.getClick().isLeftClick() || event.getClick().isRightClick())) return; //
        ItemStack currentItem = event.getCurrentItem();
        if(currentItem.getItemMeta() == null) return;
        ItemMeta meta = currentItem.getItemMeta();
        if(event.getCursor().getType() == Material.AIR) return;
        if(currentItem.getType() == Material.AIR) return;
        if(event.getCursor().getItemMeta() == null) return;
        DragAndDrops draganddrops = new DragAndDrops(event.getEventName(),event.getWhoClicked(), this.getDataFolder,event.getClickedInventory(),event.getSlot(),event.getCurrentItem(),event.getCursor(),event.getView());
        event.setCancelled(true);
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
