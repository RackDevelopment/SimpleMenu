package co.alphamc.saber.spigot.utils.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() != null && e.getInventory().getHolder() instanceof SimpleMenu)) return;
        SimpleMenu menu = (SimpleMenu) e.getInventory().getHolder();
        MenuItem item = menu.getItem(e.getSlot());
        if (item != null && item.getListener() != null) {
            item.getListener().onClick(e);
        }
    }

}
