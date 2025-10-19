package nekoserver;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class WeatherManager {
    private static BukkitTask weatherTask = null;
    
    /**
     * 启动天气锁定任务
     * @param plugin 插件实例
     */
    public static void startWeatherLockTask(JavaPlugin plugin) {
        // 如果任务已经在运行，则先停止
        if (weatherTask != null) {
            weatherTask.cancel();
        }
        
        // 启动新的定时任务，每60秒检查并锁定天气
        weatherTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            lockWeather();
        }, 0L, 1200L); // 立即开始，每60秒(1200 ticks)执行一次
        
        // 立即锁定天气
        lockWeather();
    }
    
    /**
     * 停止天气锁定任务
     */
    public static void stopWeatherLockTask() {
        if (weatherTask != null) {
            weatherTask.cancel();
            weatherTask = null;
        }
    }
    
    /**
     * 锁定所有世界的天气为晴天无雨
     */
    private static void lockWeather() {
        for (World world : Bukkit.getWorlds()) {
            // 设置天气为晴天
            world.setStorm(false);
            world.setThundering(false);
            
            // 设置天气持续时间为最大值，确保不会自然变化
            world.setWeatherDuration(Integer.MAX_VALUE);
            world.setThunderDuration(Integer.MAX_VALUE);
        }
        
        Bukkit.getLogger().info("[NekoSpawn] 天气已锁定为晴天无雨");
    }
}