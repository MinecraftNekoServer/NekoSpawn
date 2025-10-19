package nekoserver;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerMessageListener implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 取消显示玩家加入服务器的消息
        event.setJoinMessage(null);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // 取消显示玩家离开服务器的消息
        event.setQuitMessage(null);
    }
}