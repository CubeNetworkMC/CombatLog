package dev.gabbo.zkitpvp.tablist;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;

public class TabUpdater implements Runnable {

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
