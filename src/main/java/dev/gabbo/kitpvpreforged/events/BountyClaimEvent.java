package dev.gabbo.kitpvpreforged.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BountyClaimEvent extends Event
{
    private Player killed, killer;
    private long bounty;
    private static final HandlerList handlerList = new HandlerList();

    public BountyClaimEvent(Player killed, Player killer, long bounty){
        this.killed = killed;
        this.killer = killer;
        this.bounty = bounty;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlerList;
    }

    public static HandlerList getHandlerList()
    {
        return handlerList;
    }

    public Player getKilled()
    {
        return killed;
    }

    public Player getKiller()
    {
        return killer;
    }

    public long getBounty()
    {
        return bounty;
    }
}
