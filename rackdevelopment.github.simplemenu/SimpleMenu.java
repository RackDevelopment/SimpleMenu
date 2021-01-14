package rackdevelopment.github.simplemenu;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleMenu implements InventoryHolder {

    private static MenuListener listener;
    private String name;
    private String layout;
    private Inventory inventory;
    private List<MenuItem> items = new ArrayList<>();
    private Map<Integer, MenuItem> slotItems = new HashMap<>();

    public SimpleMenu(String name, String[] layout) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.layout = StringUtils.join(layout);
        this.inventory = Bukkit.createInventory(this, this.layout.toCharArray().length + 1, ChatColor.translateAlternateColorCodes('&', name));
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void addItems(MenuItem... items) {
        for (MenuItem item : items) {
            addItem(item);
        }
    }

    public static void init(JavaPlugin plugin) {
        if (listener != null) return;
        listener = new MenuListener();
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    @Override
    public Inventory getInventory() {
        int i = 0;
        for (char c : layout.toCharArray()) {
            if (items.stream().map(MenuItem::getCharacter).collect(Collectors.toList()).contains(c)) {
                inventory.setItem(i, items.stream().map(MenuItem::getItem).collect(Collectors.toList()).get(c));
                slotItems.put(i, new ArrayList<>(items).get(c));
            }
            i++;
        }
        return inventory;
    }

    public void openInventory(Player player) {
        player.openInventory(getInventory());
    }

    public static MenuListener getListener() {
        return listener;
    }

    public String getName() {
        return name;
    }

    public MenuItem getItem(int slot) {
        if (slotItems.containsKey(slot)) return slotItems.get(slot);
        return null;
    }

    public String getLayout() {
        return layout;
    }

    public List<MenuItem> getItems() {
        return items;
    }

}
