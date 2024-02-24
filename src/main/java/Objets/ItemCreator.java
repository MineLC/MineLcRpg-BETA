package Objets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCreator {
    private ItemStack itemStack;

    public ItemCreator(Material material, String displayName,String lore1,String lore2,String lore3,String lore4,String lore5,String lore6,String lore7,String lore8) {
        material = material != null ? material : Material.BARRIER;
        lore1 = lore1 != null ? lore1 : "&f";
        lore2 = lore2 != null ? lore2 : "&f";
        lore3 = lore3 != null ? lore3 : "&f";
        lore4 = lore4 != null ? lore4 : "&f";
        lore5 = lore5 != null ? lore5 : "&f";
        lore6 = lore6 != null ? lore6 : "&f";
        lore7 = lore7 != null ? lore7 : "&f";
        this.itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        //###################################################
        list.add(ChatColor.translateAlternateColorCodes('&',lore1));
        list.add(ChatColor.translateAlternateColorCodes('&',lore2));
        list.add(ChatColor.translateAlternateColorCodes('&',lore3));
        list.add(ChatColor.translateAlternateColorCodes('&',lore4));
        list.add(ChatColor.translateAlternateColorCodes('&',lore5));
        list.add(ChatColor.translateAlternateColorCodes('&',lore6));
        list.add(ChatColor.translateAlternateColorCodes('&',lore7));
        list.add(ChatColor.translateAlternateColorCodes('&',lore8));
        //###################################################
        assert meta != null;
        meta.setLore(list);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',displayName));
        itemStack.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
