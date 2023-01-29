package dev.gabbo.zkitpvp.commands.impl.subcommands;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.commands.api.Subcommand;
import dev.gabbo.zkitpvp.data.PlayerData;
import dev.gabbo.zkitpvp.utils.ChatUtils;
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
