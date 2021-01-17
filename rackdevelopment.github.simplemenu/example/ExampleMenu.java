package rackdevelopment.github.simplemenu.example;

import co.alphamc.saber.spigot.utils.ItemBuilder; // Please note the ItemBuilder is not included in these files.
import rackdevelopment.github.simplemenu.MenuItem;
import rackdevelopment.github.simplemenu.SimpleMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ExampleMenu {

    public static void openInventory(Player player) {
        SimpleMenu menu = new SimpleMenu("&a&lExample Menu", new String[]{"aaabbbccc", "aaabbbccc", "aaabbbccc"}, 3, false);
        MenuItem itemA = new MenuItem('a', ItemBuilder.start(Material.WOOL).data((short) 6).build());
        itemA.setListener((event) -> {
            event.setCancelled(true);
        });
        MenuItem itemB = new MenuItem('b', ItemBuilder.start(Material.WOOL).build());
        itemB.setListener((event) -> {
            event.setCancelled(true);
        });
        MenuItem itemC = new MenuItem('c', ItemBuilder.start(Material.WOOL).data((short) 14).build());
        itemC.setListener((event) -> {
            event.setCancelled(true);
        });
        menu.addItems(itemA, itemB, itemC);
        menu.openInventory(player);
    }

}
