package dev.gabbo.zkitpvp.items;

import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ItemMaker implements Supplier<ItemStack> {

    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private List<String> lore;

    public ItemMaker(Material material) {
        this.lore = new ArrayList<>();

        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemMaker(Material material, String displayName) {
        this.lore = new ArrayList<>();

        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();

        this.itemMeta.setDisplayName(ChatUtils.getColoredText(displayName));
    }

    public ItemMaker addLoreLine(String line) {
        lore.add(ChatUtils.getColoredText(line));
        return this;
    }

    public ItemMaker setUnbreakable(boolean unbreakable) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);

        return this;
    }

    public ItemMaker addEnchant(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemMaker addFlag(ItemFlag flag) {
        this.itemMeta.addItemFlags(flag);
        return this;
    }

    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public ItemMaker setLore(String... lines) {
        setLore(Arrays.asList(lines));
        return this;
    }

    public ItemMaker setLore(List<String> lines) {
        this.lore = lines;
        return this;
    }

    @Override
    public ItemStack get() {
        this.itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);

        return this.itemStack;
    }

}