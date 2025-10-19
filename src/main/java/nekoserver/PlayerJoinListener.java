package nekoserver;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final SpawnManager spawnManager;

    public PlayerJoinListener(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location spawnLocation = spawnManager.getSpawnLocation();
        
        // 如果出生点已设置，则传送玩家到出生点
        if (spawnLocation != null) {
            player.teleport(spawnLocation);
        }
    }
}