package dev.gabbo.zkitpvp.commands.impl;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.commands.api.KitPvPCommand;
import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FixCommand extends KitPvPCommand {

    public FixCommand() {
        super(KitPvP.getInstance(), "fix", "kitpvp.commands.fix", true);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        int price = KitPvP.getFileManager().getConfig().getInt("fix.cost", 0);
        double balance = KitPvP.getEconomy().getBalance(player);

        if (balance < price) {
            player.sendMessage(ChatUtils.getFormattedText("fix.not-enough-money"));
            return;
        }

        PlayerInventory inventory = player.getInventory();

        Arrays.stream(inventory.getContents()).filter(x -> x != null && !x.getType().isBlock() && x.getType().getMaxDurability() > 0).forEach(x -> x.setDurability((short) 0));
        Arrays.stream(inventory.getArmorContents()).filter(Objects::nonNull).forEach(x -> x.setDurability((short) 0));

        KitPvP.getEconomy().withdrawPlayer(player, price);
        player.sendMessage(ChatUtils.getFormattedText("fix.repaired"));
    }

}
