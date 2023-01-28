package dev.gabbo.kitpvpreforged.commands.impl;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.commands.api.EnumCommand;
import dev.gabbo.kitpvpreforged.data.PlayerData;
import dev.gabbo.kitpvpreforged.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends EnumCommand {
    public SpawnCommand() {
        super(KitPvP.getInstance(), "spawn", "kitpvp.commands.spawn", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("spawn.no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        Location spawn = (Location) KitPvP.getFileManager().getConfig().get("spawn-location");

        if (spawn == null)
        {
            player.sendMessage(ChatUtils.getFormattedText("spawn.no-spawn-found"));
            return;
        }

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId().toString());
        data.atSpawn = true;

        KitPvP.getDataManager().updateData(data);
        player.teleport(spawn);
    }
}
