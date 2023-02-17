package dev.gabbo.zkitpvp.commands.impl;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.commands.api.CommandHandler;
import dev.gabbo.zkitpvp.commands.api.KitPvPCommand;
import dev.gabbo.zkitpvp.commands.impl.subcommands.*;
import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MainCommand extends KitPvPCommand {

    public MainCommand() {
        super(KitPvP.getInstance(), "kitpvp", "kitpvp.commands.admin", false);

        setNoSubCommandFoundMessage(ChatUtils.getFormattedText("admin.no-sub-command-found"));
        setNoPermissionMessage(ChatUtils.getColoredText("&7Running &bKitPvP Reforged &7version &a1.2.1 &7by &cImGqbbo"));

        CommandHandler.addSubCommand(this, new SetStatsCommand());
        CommandHandler.addSubCommand(this, new SetSpawnCommand());
        CommandHandler.addSubCommand(this, new SetBountyCommand());
        CommandHandler.addSubCommand(this, new BuildCommand());
        CommandHandler.addSubCommand(this, new AlertCommand());
        CommandHandler.addSubCommand(this, new ReloadCommand());
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() != 0) return;

        KitPvP.getFileManager().getMessages().getStringList("admin.help-command")
                .forEach(msg -> sender.sendMessage(ChatUtils.getColoredText(msg)));
    }

}
