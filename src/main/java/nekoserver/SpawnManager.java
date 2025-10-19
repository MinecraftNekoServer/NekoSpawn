package nekoserver;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;

    public SpawnManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        // 初始化配置文件
        plugin.saveDefaultConfig();
    }

    /**
     * 设置出生点位置
     * @param player 玩家对象
     * @return 是否设置成功
     */
    public boolean setSpawn(Player player) {
        Location location = player.getLocation();
        String worldName = location.getWorld().getName();
        
        // 保存位置信息到配置文件
        config.set("spawn.world", worldName);
        config.set("spawn.x", location.getX());
        config.set("spawn.y", location.getY());
        config.set("spawn.z", location.getZ());
        config.set("spawn.yaw", location.getYaw());
        config.set("spawn.pitch", location.getPitch());
        
        // 保存配置文件
        plugin.saveConfig();
        return true;
    }

    /**
     * 获取出生点位置
     * @return 出生点位置，如果未设置则返回null
     */
    public Location getSpawnLocation() {
        // 检查配置文件中是否有出生点信息
        if (!config.contains("spawn.world")) {
            return null;
        }

        String worldName = config.getString("spawn.world");
        World world = Bukkit.getWorld(worldName);
        
        // 如果世界不存在，返回null
        if (world == null) {
            return null;
        }

        double x = config.getDouble("spawn.x");
        double y = config.getDouble("spawn.y");
        double z = config.getDouble("spawn.z");
        float yaw = (float) config.getDouble("spawn.yaw");
        float pitch = (float) config.getDouble("spawn.pitch");
        
        // 如果所有坐标都为0，则认为未设置
        if (x == 0.0 && y == 0.0 && z == 0.0 && yaw == 0.0 && pitch == 0.0) {
            return null;
        }

        return new Location(world, x, y, z, yaw, pitch);
    }
}