package nekoserver;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    // 控制是否阻止生物生成的标志
    private static boolean preventSpawning = false;
    
    // 自动移除生物任务
    private static BukkitTask autoRemoveTask = null;
    
    /**
     * 移除所有生物实体
     * @return 被移除的生物数量
     */
    public static int removeAllEntities() {
        int count = 0;
        
        // 遍历所有世界
        for (World world : Bukkit.getWorlds()) {
            // 获取所有实体
            List<Entity> entities = new ArrayList<>(world.getEntities());
            
            // 遍历并移除生物实体
            for (Entity entity : entities) {
                // 检查是否为生物实体（排除玩家）
                if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                    entity.remove();
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * 设置是否阻止生物生成
     * @param prevent 是否阻止生物生成
     */
    public static void setPreventSpawning(boolean prevent) {
        preventSpawning = prevent;
    }
    
    /**
     * 检查是否阻止生物生成
     * @return 是否阻止生物生成
     */
    public static boolean isPreventSpawning() {
        return preventSpawning;
    }
    
    /**
     * 启动自动移除生物任务
     * @param plugin 插件实例
     */
    public static void startAutoRemoveTask(JavaPlugin plugin) {
        // 如果任务已经在运行，则先停止
        if (autoRemoveTask != null) {
            autoRemoveTask.cancel();
        }
        
        // 启动新的定时任务，每30秒移除一次生物
        autoRemoveTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            int count = removeAllEntities();
            if (count > 0) {
                //Bukkit.getLogger().info("[NekoSpawn] 自动移除了 " + count + " 个生物实体");
            }
        }, 600L, 600L); // 延迟30秒(600 ticks)后开始，每30秒(600 ticks)执行一次
    }
    
    /**
     * 停止自动移除生物任务
     */
    public static void stopAutoRemoveTask() {
        if (autoRemoveTask != null) {
            autoRemoveTask.cancel();
            autoRemoveTask = null;
        }
    }
}