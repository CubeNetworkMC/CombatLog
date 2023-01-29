package dev.gabbo.zkitpvp.tasks;

import dev.gabbo.zkitpvp.KitPvP;
import dev.gabbo.zkitpvp.api.ReforgedBlock;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.ArrayList;
import java.util.List;

public class BlockTask {
    public BlockTask() {
        Bukkit.getScheduler().runTaskTimer(KitPvP.getInstance(), () ->
        {
            for (int i = 0; i < blockList.size(); i++) {
                ReforgedBlock block = blockList.get(i);
                block.timer = Math.floor((block.timer - 0.1) * 10) / 10;

                double divisor = block.maxTimer / 10, oldStatus = block.status;
                if (Math.floor(block.timer) == block.timer && block.timer % divisor == 0.0) {
                    block.status++;
                }

                if (oldStatus != block.status) {
                    Block normalBlock = block.getBlock();

                    BlockPosition position = new BlockPosition(normalBlock.getX(), normalBlock.getY(), normalBlock.getZ());
                    PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(i, position, block.status);

                    Bukkit.getOnlinePlayers().forEach(player ->
                    {
                        CraftPlayer craftPlayer = (CraftPlayer) player;
                        EntityPlayer entityPlayer = craftPlayer.getHandle();

                        entityPlayer.playerConnection.sendPacket(packet);
                    });
                }

                if (block.timer == 0.0) {
                    block.getBlock().setType(Material.AIR);
                    blockList.remove(i);
                }
            }
        }, 0L, 2L);
    }

    public void addBlock(ReforgedBlock block) {
        blockList.add(block);
    }

    private final List<ReforgedBlock> blockList = new ArrayList<>();
}
