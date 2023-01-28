package dev.gabbo.kitpvpreforged.tablist;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.utils.ChatUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TabUpdater extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            TabComponent component = new TabComponent();

            KitPvP.getFileManager().getConfig().getStringList("tab-list.header")
                    .forEach(header -> component.addLineToHeader(ChatUtils.formatMessage(player, header)));
            KitPvP.getFileManager().getConfig().getStringList("tab-list.footer")
                    .forEach(footer -> component.addLineToFooter(ChatUtils.formatMessage(player, footer)));

            component.sendToPlayer(player);
        });
    }

}
