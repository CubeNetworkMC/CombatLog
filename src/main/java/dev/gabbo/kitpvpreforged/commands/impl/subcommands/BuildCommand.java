package dev.gabbo.kitpvpreforged.commands.impl.subcommands;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.commands.api.Subcommand;
import dev.gabbo.kitpvpreforged.data.PlayerData;
import dev.gabbo.kitpvpreforged.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BuildCommand extends Subcommand {

    public BuildCommand() {
        super("kitpvp", "build", "kitpvp.commands.build", true);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId().toString());
        data.isBuilder = !data.isBuilder;

        sender.sendMessage(ChatUtils.getFormattedText("anti-build.build-mode-" + (data.isBuilder ? "on" : "off")));

        KitPvP.getDataManager().updateData(data);
    }
}
