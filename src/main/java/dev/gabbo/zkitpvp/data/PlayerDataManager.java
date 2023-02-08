package dev.gabbo.zkitpvp.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerDataManager {

    public PlayerData getPlayerData(UUID uuid) {
        PlayerData data = filter(playerDataList, uuid);

        if (data == null) {
            playerDataList.add(new PlayerData(uuid));
            return getPlayerData(uuid);
        }

        return data;
    }

    private PlayerData filter(List<PlayerData> list, UUID uuid) {
        for (int i = 0; i < list.size(); i++) {
            PlayerData data = playerDataList.get(i);
            if (data != null && data.getUUID().equals(uuid)) {
                return data;
            }
        }

        return null;
    }

    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId());
    }

    public List<PlayerData> getAllData() {
        return playerDataList;
    }

    public void updateData(PlayerData data) {
        if (data == null) return;
        PlayerData playerData = filter(playerDataList, data.getUUID());

        playerDataList.remove(playerData);
        playerDataList.add(data);
    }

    List<PlayerData> playerDataList = new ArrayList<>();
}
