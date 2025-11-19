package nekoserver;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerMoveListener implements Listener {
    private final SpawnManager spawnManager;
    private static final int Y_THRESHOLD = 0;
    
    public PlayerMoveListener(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        
        // 检查Y坐标是否小于等于阈值
        if (to != null && to.getY() <= Y_THRESHOLD) {
            // 获取出生点位置
            Location spawnLocation = spawnManager.getSpawnLocation();
            
            // 如果出生点已设置，则传送玩家到出生点
            if (spawnLocation != null) {
                player.teleport(spawnLocation);
                // 取消事件以防止玩家继续下落
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        
        // 检查目标位置的Y坐标是否小于等于阈值
        if (to != null && to.getY() <= Y_THRESHOLD) {
            // 获取出生点位置
            Location spawnLocation = spawnManager.getSpawnLocation();
            
            // 如果出生点已设置，则传送玩家到出生点
            if (spawnLocation != null) {
                player.teleport(spawnLocation);
                // 取消传送事件以防止玩家传送到危险位置
                event.setCancelled(true);
            }
        }
    }
}