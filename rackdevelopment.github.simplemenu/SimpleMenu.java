package rackdevelopment.github.simplemenu;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class SimpleMenu implements InventoryHolder {

    private static MenuListener listener;
    private String name;
    private String layout;
    private Inventory inventory;
    private Map<Character, MenuItem> items = new HashMap<>();
    private Map<Integer, MenuItem> slotItems = new HashMap<>();
    private boolean paginated;
    public Map<Integer, SimpleMenu> pages = new HashMap<>();

    public SimpleMenu(String name, String[] layout, int rows, boolean paginated) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.layout = StringUtils.join(layout);
        this.paginated = paginated;
        this.inventory = Bukkit.createInventory(this, rows * 9, ChatColor.translateAlternateColorCodes('&', name));
    }

    public void addItem(MenuItem item) {
        items.put(item.getCharacter(), item);
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
            if (items.containsKey(c)) {
                inventory.setItem(i, items.get(c).getItem());
                slotItems.put(i, items.get(c));
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

    public Map<Character, MenuItem> getItems() {
        return items;
    }

    public boolean isPaginated() {
        return paginated;
    }

}
