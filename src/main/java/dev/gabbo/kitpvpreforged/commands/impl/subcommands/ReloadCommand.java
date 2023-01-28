package dev.gabbo.kitpvpreforged.commands.impl.subcommands;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.commands.api.Subcommand;
import dev.gabbo.kitpvpreforged.utils.ChatUtils;
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
