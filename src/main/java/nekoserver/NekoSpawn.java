package nekoserver;

import org.bukkit.plugin.java.JavaPlugin;

public final class NekoSpawn extends JavaPlugin {
    private SpawnManager spawnManager;
    private static final String BUNGEE_CHANNEL = "BungeeCord";

    @Override
    public void onEnable() {
        System.out.println("[NekoSpawn] 插件已启用！");
        
        // 初始化出生点管理器
        spawnManager = new SpawnManager(this);
        
        // 注册指令
        this.getCommand("spawn").setExecutor(new SetSpawnCommand(spawnManager));
        
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(spawnManager), this);
        getServer().getPluginManager().registerEvents(new EntitySpawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(spawnManager), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        getServer().getPluginManager().registerEvents(new MenuProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new BuildPermissionListener(), this);
        
        // 注册插件通道
        getServer().getMessenger().registerOutgoingPluginChannel(this, BUNGEE_CHANNEL);
        
        // 启动自动移除生物功能
        EntityManager.startAutoRemoveTask(this);
        
        // 设置默认阻止生物生成
        EntityManager.setPreventSpawning(true);
        
        // 启动天气锁定功能
        WeatherManager.startWeatherLockTask(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[NekoSpawn] 插件已禁用！");
        EntityManager.stopAutoRemoveTask();
        
        // 停止天气锁定功能
        WeatherManager.stopWeatherLockTask();
        
        // 注销插件通道
        getServer().getMessenger().unregisterOutgoingPluginChannel(this, BUNGEE_CHANNEL);
    }
    
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
}
