package dev.gabbo.kitpvpreforged.tasks;

import dev.gabbo.kitpvpreforged.KitPvP;
import dev.gabbo.kitpvpreforged.data.PlayerData;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneralTask extends BukkitRunnable {

    private final double combatTimer = KitPvP.getFileManager().getConfig().getDouble("ender-pearl.timer");
    private final double enderTimer = KitPvP.getFileManager().getConfig().getDouble("ender-pearl.timer");

    @Override
    public void run() {
        if (KitPvP.getDataManager().getAllData().size() == 0) return;

        for (int i = 0; i < KitPvP.getDataManager().getAllData().size(); i++) {
            PlayerData data = KitPvP.getDataManager().getAllData().get(i);
            if (data == null) return;

            if (data.combatTimestamp != 0) {
                long time = (long) (data.combatTimestamp + (combatTimer * 1000L));
                long now = System.currentTimeMillis();

                long millis = time - now;
                if (millis > 0) {
                    data.combatTimer = millis;
                } else {
                    data.combatTimer = 0;
                    data.combatTimestamp = 0;
                }
            }

            if (data.enderTimestamp != 0) {
                long time = (long) (data.enderTimestamp + (enderTimer * 1000L));
                long now = System.currentTimeMillis();

                long millis = time - now;
                if (millis > 0) {
                    data.combatTimer = millis;
                } else {
                    data.combatTimer = 0;
                    data.combatTimestamp = 0;
                }
            }

            data.inEnderCooldown = data.enderTimestamp != 0;
            data.inCombat = data.combatTimestamp != 0;

            KitPvP.getDataManager().updateData(data);
        }
    }

}
