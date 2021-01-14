package rackdevelopment.github.simplemenu;

import org.bukkit.inventory.ItemStack;

public class MenuItem {

    private final Character character;
    private final ItemStack item;
    private ItemListener listener;

    public MenuItem(Character character, ItemStack item) {
        this.character = character;
        this.item = item;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public Character getCharacter() {
        return character;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemListener getListener() {
        return listener;
    }

}
