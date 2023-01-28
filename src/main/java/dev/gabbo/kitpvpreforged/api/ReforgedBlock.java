package dev.gabbo.kitpvpreforged.api;

import org.bukkit.block.Block;

public class ReforgedBlock {
    public ReforgedBlock(Block block, int timer) {
        this.block = block;
        this.status = 0;
        this.timer = timer;
        this.maxTimer = timer;
    }

    public double timer, maxTimer;
    public int status;
    private final Block block;
    public Block getBlock() {
        return block;
    }
}
