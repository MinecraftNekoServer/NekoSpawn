package nekoserver;

import org.bukkit.plugin.java.JavaPlugin;

public final class NekoSpawn extends JavaPlugin {
    private SpawnManager spawnManager;

    @Override
    public void onEnable() {
        System.out.println("[NekoSpawn] Plugin has been enabled!");
        
        // 初始化出生点管理器
        spawnManager = new SpawnManager(this);
        
        // 注册指令
        this.getCommand("spawn").setExecutor(new SetSpawnCommand(spawnManager));
        
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(spawnManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[NekoSpawn] Plugin has been disabled!");
    }
    
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
}
