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
    public long endCombatTimestamp, endEnderTimestamp;
    public List<Player> assisters = new ArrayList<>();
    private Player lastPlayer;
    private long bounty;

    public PlayerData(String player) {
        this.player = player;

        YamlConfiguration configuration = new YamlConfiguration();
        File file = new File(KitPvP.getInstance().getDataFolder().getAbsolutePath() + "/data", player);

        if (file.exists()) {
            try {
                configuration.load(file);

                kills = configuration.getInt("kills", 0);
                deaths = configuration.getInt("deaths", 0);
                streak = configuration.getInt("streak", 0);
                bounty = configuration.getLong("bounty", 0);

                pickupArrows = configuration.getBoolean("pickup-arrows", true);
                pickupGoldenApple = configuration.getBoolean("pickup-apples", true);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBounty(long bounty) {
        this.bounty += bounty;
    }

    public long getBounty() {
        return bounty;
    }

    public void setBounty(long bounty) {
        this.bounty = bounty;
    }

    public boolean isBuilder = false, inCombat = false, inEnderCooldown = false, atSpawn = true, pickupArrows = true, pickupGoldenApple = true;
    public int kills = 0, deaths = 0, streak = 0;

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(Player lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public String getPlayer() {
        return player;
    }

    private final String player;
}
