package dev.gabbo.zkitpvp.tasks;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.data.PlayerData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class SaveTask extends BukkitRunnable {

    @Override
    public void run() {
        for (int i = 0; i < KitPvP.getDataManager().getAllData().size(); i++) {
            if (i >= KitPvP.getDataManager().getAllData().size()) break;

            try {
                PlayerData data = KitPvP.getDataManager().getAllData().get(i);
                if (data == null) return;

                String player = data.getPlayer();

                YamlConfiguration configuration = new YamlConfiguration();
                File file = new File(KitPvP.getInstance().getDataFolder().getAbsolutePath() + "/data", player);

                configuration.set("kills", data.kills);
                configuration.set("deaths", data.deaths);
                configuration.set("streak", data.streak);
                configuration.set("bounty", data.getBounty());

                configuration.set("pickup-arrows", data.pickupArrows);
                configuration.set("pickup-apples", data.pickupGoldenApple);

                KitPvP.getFileManager().saveFile(configuration, file);
            } catch (IndexOutOfBoundsException ignored) {

            }
        }
    }

}
