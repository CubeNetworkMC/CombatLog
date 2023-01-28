package dev.gabbo.kitpvpreforged.commands.impl;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.commands.api.EnumCommand;
import dev.gabbo.kitpvpreforged.data.PlayerData;
import dev.gabbo.kitpvpreforged.inventory.InventoryMaker;
import dev.gabbo.kitpvpreforged.items.ItemMaker;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class DropSettingsCommand extends EnumCommand {

    public DropSettingsCommand() {
        super(KitPvP.getInstance(), "dropsettings", "kitpvp.commands.dropsettings", true);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) return;

        Player player = (Player) sender;
        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId().toString());

        ItemMaker apple = new ItemMaker(Material.GOLDEN_APPLE)
                .addLoreLine(KitPvP.getFileManager().getMessages().getString("drop-settings.enabled")
                        .replaceAll("%enabled%", String.valueOf(data.pickupArrows)));

        ItemMaker arrow = new ItemMaker(Material.ARROW)
                .addLoreLine(KitPvP.getFileManager().getMessages().getString("drop-settings.enabled")
                        .replaceAll("%enabled%", String.valueOf(data.pickupArrows)));

        Inventory inventory = new InventoryMaker(27, "Impostazioni raccolta oggetti")
                .addItemStack(12, apple.get())
                .addAction(12, (event) -> {
                    Inventory clickedInventory = event.getClickedInventory();
                    PlayerData clickerData = KitPvP.getDataManager().getPlayerData(event.getWhoClicked().getUniqueId().toString());

                    clickerData.pickupGoldenApple = !clickerData.pickupGoldenApple;

                    event.setCancelled(true);
                    KitPvP.getDataManager().updateData(clickerData);

                    ItemMaker apple2 = new ItemMaker(Material.GOLDEN_APPLE)
                            .addLoreLine(KitPvP.getFileManager().getMessages().getString("drop-settings.enabled")
                                    .replaceAll("%enabled%", String.valueOf(data.pickupGoldenApple)));

                    ItemMaker arrow2 = new ItemMaker(Material.ARROW)
                            .addLoreLine(KitPvP.getFileManager().getMessages().getString("drop-settings.enabled")
                                    .replaceAll("%enabled%", String.valueOf(data.pickupArrows)));

                    clickedInventory.setItem(12, apple2.get());
                    clickedInventory.setItem(14, arrow2.get());
                })
                .addItemStack(14, arrow.get())
                .addAction(14, (event) -> {
                    Inventory clickedInventory = event.getClickedInventory();
                    PlayerData clickerData = KitPvP.getDataManager().getPlayerData(event.getWhoClicked().getUniqueId().toString());

                    clickerData.pickupArrows = !clickerData.pickupArrows;

                    event.setCancelled(true);
                    KitPvP.getDataManager().updateData(clickerData);

                    ItemMaker apple2 = new ItemMaker(Material.GOLDEN_APPLE)
                            .addLoreLine(KitPvP.getFileManager().getMessages().getString("drop-settings.enabled")
                                    .replaceAll("%enabled%", String.valueOf(data.pickupGoldenApple)));

                    ItemMaker arrow2 = new ItemMaker(Material.ARROW)
                            .addLoreLine(KitPvP.getFileManager().getMessages().getString("drop-settings.enabled")
                                    .replaceAll("%enabled%", String.valueOf(data.pickupArrows)));

                    clickedInventory.setItem(12, apple2.get());
                    clickedInventory.setItem(14, arrow2.get());
                })
                .get();

        player.openInventory(inventory);
    }
}
