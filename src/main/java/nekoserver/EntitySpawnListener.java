package nekoserver;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EntitySpawnListener implements Listener {
    
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        // 强制阻止所有生物生成
        event.setCancelled(true);
    }
}