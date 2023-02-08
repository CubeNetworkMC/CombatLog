package dev.gabbo.zkitpvp.commands.impl;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.commands.api.KitPvPCommand;
import dev.gabbo.zkitpvp.data.PlayerData;
import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends KitPvPCommand {
    public SpawnCommand() {
        super(KitPvP.getInstance(), "spawn", "kitpvp.commands.spawn", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("spawn.no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        Location spawn = (Location) KitPvP.getFileManager().getConfig().get("spawn-location");

        if (spawn == null) {
            player.sendMessage(ChatUtils.getFormattedText("spawn.no-spawn-found"));
            return;
        }

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());
        data.atSpawn = true;

        KitPvP.getDataManager().updateData(data);
        player.teleport(spawn);
    }
}
