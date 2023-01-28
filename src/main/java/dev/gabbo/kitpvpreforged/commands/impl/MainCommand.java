package dev.gabbo.kitpvpreforged.commands.impl;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.commands.api.CommandHandler;
import dev.gabbo.kitpvpreforged.commands.api.EnumCommand;
import dev.gabbo.kitpvpreforged.commands.impl.subcommands.*;
import dev.gabbo.kitpvpreforged.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MainCommand extends EnumCommand {

    public MainCommand() {
        super(KitPvP.getInstance(), "kitpvp", "kitpvp.commands.admin", false);

        setNoSubCommandFoundMessage(ChatUtils.getFormattedText("admin.no-sub-command-found"));
        setNoPermissionMessage(ChatUtils.getColoredText("&7Running &bKitPvP Reforged &7version &a1.1 &7by &cImGqbbo"));

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
