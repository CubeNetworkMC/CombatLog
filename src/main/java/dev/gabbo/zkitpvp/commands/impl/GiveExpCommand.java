package dev.gabbo.zkitpvp.commands.impl;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.commands.api.KitPvPCommand;
import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveExpCommand extends KitPvPCommand {

    public GiveExpCommand() {
        super(KitPvP.getInstance(), "giveexp", "kitpvp.commands.giveexp", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("giveexp.no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        if (args.size() != 2) {
            player.sendMessage(ChatUtils.getFormattedText("giveexp.usage"));
            return;
        }

        String name = args.get(0);
        if (Bukkit.getPlayer(name) == null) {
            player.sendMessage(ChatUtils.getFormattedText("giveexp.player-not-found"));
            return;
        }

        Player target = Bukkit.getPlayer(name);
        if (!NumberUtils.isNumber(args.get(1))) {
            player.sendMessage(ChatUtils.getFormattedText("giveexp.usage"));
            return;
        }

        int levels = player.getLevel();
        int amount = Integer.parseInt(args.get(1));

        if (levels < amount) {
            player.sendMessage(ChatUtils.getFormattedText("giveexp.not-enough-levels"));
            return;
        }

        player.setLevel(levels - amount);
        target.setLevel(target.getLevel() + amount);

        player.sendMessage(ChatUtils.getFormattedText("giveexp.sent")
                .replaceAll("%levels%", String.valueOf(amount))
                .replaceAll("%name%", target.getName()));
    }

}
