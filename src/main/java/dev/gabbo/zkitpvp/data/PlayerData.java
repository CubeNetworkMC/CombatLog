package dev.gabbo.zkitpvp.data;

import dev.gabbo.zkitpvp.KitPvP;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    public PlayerData(String player) {
        this.player = player;
        this.assisters = new ArrayList<>();

        YamlConfiguration configuration = new YamlConfiguration();
        File file = new File(KitPvP.getInstance().getDataFolder().getAbsolutePath() + "/data", player);

        if (file.exists()) {
            try {
                configuration.load(file);

                kills = configuration.getInt("kills");
                deaths = configuration.getInt("deaths");
                streak = configuration.getInt("streak");
                bounty = configuration.getLong("bounty");

                pickupArrows = configuration.getBoolean("pickup-arrows");
                pickupGoldenApple = configuration.getBoolean("pickup-apples");
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        this.atSpawn = true;
    }

    public long combatTimestamp, enderTimestamp;
    public double combatTimer, enderTimer;
    public boolean isBuilder = false, inCombat = false, inEnderCooldown = false, atSpawn = true, pickupArrows = true, pickupGoldenApple = true;
    public int kills = 0, deaths = 0, streak = 0;
    public long bounty;
    public List<Player> assisters;
    public Player lastPlayer;

    private final String player;

    public String getPlayer() {
        return player;
    }
}
