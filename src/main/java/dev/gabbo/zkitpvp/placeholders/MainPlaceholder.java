package dev.gabbo.zkitpvp.placeholders;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.data.PlayerData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class MainPlaceholder extends PlaceholderExpansion {
    @Override
    public String getAuthor() {
        return "ImGqbbo";
    }

    @Override
    public String getIdentifier() {
        return "kitpvp";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getPlayer().getUniqueId().toString());
        switch (params) {
            case "bounty":
                return String.valueOf(data.bounty);
            case "bounty_formatted":
                return formatNumber(data.bounty);
            case "kills":
                return String.valueOf(data.kills);
            case "deaths":
                return String.valueOf(data.deaths);
            case "streak":
                return String.valueOf(data.streak);
            case "combat":
                return String.valueOf(Math.floor(data.combatTimer / 100) / 10);
            case "enderpearl":
                return String.valueOf(Math.floor(data.enderTimer / 100) / 10);
        }

        return null;
    }

    private String formatNumber(long value) {
        String number = String.format("%,d", value);
        String[] commas = new String[]{"K", "M", "B", "T", "Q"};

        String[] split = number.split(",");
        if (split.length == 1) {
            return String.valueOf(value);
        }
        return split[0] + "." + split[1].charAt(0) + commas[split.length - 2];
    }
}
