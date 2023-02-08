package dev.gabbo.zkitpvp;

import dev.gabbo.zkitpvp.blocks.BlockListener;
import dev.gabbo.zkitpvp.blocks.BlockTask;
import dev.gabbo.zkitpvp.commands.api.KitPvPCommand;
import dev.gabbo.zkitpvp.commands.impl.*;
import dev.gabbo.zkitpvp.data.PlayerDataManager;
import dev.gabbo.zkitpvp.inventory.InventoryListener;
import dev.gabbo.zkitpvp.listeners.PlayerListener;
import dev.gabbo.zkitpvp.placeholders.MainPlaceholder;
import dev.gabbo.zkitpvp.tablist.TabUpdater;
import dev.gabbo.zkitpvp.tasks.GeneralTask;
import dev.gabbo.zkitpvp.tasks.SaveTask;
import dev.gabbo.zkitpvp.utils.FileManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class KitPvP extends JavaPlugin {

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(instance);

        Arrays.asList(new MainCommand(), new SpawnCommand(), new DropSettingsCommand(), new FixCommand(), new GiveExpCommand())
                .forEach(KitPvPCommand::registerExecutor);

        Arrays.asList(new BlockListener(), new PlayerListener(), new InventoryListener())
                .forEach(event -> Bukkit.getPluginManager().registerEvents(event, this));

        dataManager = new PlayerDataManager();
        blockManager = new BlockTask();

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new MainPlaceholder().register();
        }

        if (fileManager.getConfig().getBoolean("tab-list.enabled")) {
            Bukkit.getScheduler().runTaskTimer(this, new TabUpdater(), 10L, 10L);
        }

        saveManager = new SaveTask();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, saveManager, 6000L, 6000L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new GeneralTask(), 2L, 2L);

        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            RegisteredServiceProvider<Economy> service = getServer().getServicesManager().getRegistration(Economy.class);
            if (service != null) {
                economy = service.getProvider();
            }
        }
    }

    public void reloadConfiguration() {
        fileManager = new FileManager(instance);
    }

    @Override
    public void onDisable() {
        if (saveManager == null) return;
        saveManager.run();
    }

    private static KitPvP instance;

    public static KitPvP getInstance() {
        return instance;
    }

    private static PlayerDataManager dataManager;

    public static PlayerDataManager getDataManager() {
        return dataManager;
    }

    private static BlockTask blockManager;

    public static BlockTask getBlockManager() {
        return blockManager;
    }

    private static SaveTask saveManager;

    public static SaveTask getSaveManager() {
        return saveManager;
    }

    private static FileManager fileManager;

    public static FileManager getFileManager() {
        return fileManager;
    }

    private static Economy economy;

    public static Economy getEconomy() {
        return economy;
    }
}
