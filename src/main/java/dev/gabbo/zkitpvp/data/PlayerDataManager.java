package dev.gabbo.zkitpvp.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataManager {

    public PlayerData getPlayerData(String player) {
        PlayerData data = filter(playerDataList, player);

        if (data == null) {
            playerDataList.add(new PlayerData(player));
            return getPlayerData(player);
        }

        return data;
    }

    private PlayerData filter(List<PlayerData> list, String player) {
        for (int i = 0; i < list.size(); i++) {
            PlayerData data = playerDataList.get(i);
            if (data != null && data.getPlayer().equals(player)) {
                return data;
            }
        }

        return null;
    }

    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId().toString());
    }

    public List<PlayerData> getAllData() {
        return playerDataList;
    }

    public void updateData(PlayerData data) {
        if (data == null) return;
        //PlayerData playerData = filter(playerDataList, data.getPlayer());

        playerDataList.remove(data);
        playerDataList.add(data);
    }

    List<PlayerData> playerDataList = new ArrayList<>();
}
