package dev.gabbo.zkitpvp.commands.impl.subcommands;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.commands.api.Subcommand;
import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand extends Subcommand {

    public ReloadCommand() {
        super("kitpvp", "reload", "kitpvp.commands.reload", false);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        KitPvP.getInstance().reloadConfiguration();
        sender.sendMessage(ChatUtils.getFormattedText("admin.plugin-reloaded"));
    }

}
