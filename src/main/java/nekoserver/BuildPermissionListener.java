package nekoserver;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuildPermissionListener implements Listener {
    private static final String BUILD_PERMISSION = "nekospawn.build";
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        // 检查玩家是否有建筑权限
        if (!player.hasPermission(BUILD_PERMISSION)) {
            event.setCancelled(true);
            //player.sendMessage("§c你没有建筑权限喵~");
        }
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        // 检查玩家是否有建筑权限
        if (!player.hasPermission(BUILD_PERMISSION)) {
            event.setCancelled(true);
            //player.sendMessage("§c你没有建筑权限喵~");
        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // 检查玩家是否有建筑权限
        if (!player.hasPermission(BUILD_PERMISSION)) {
            // 取消与方块的交互（如打开箱子、拉杆等）
            if (event.hasBlock()) {
                event.setCancelled(true);
                //player.sendMessage("§c你没有建筑权限喵~");
            }
        }
    }
    
    @EventHandler
    public void onEntityInteract(EntityInteractEvent event) {
        // 检查是否是玩家踩踏农田
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // 检查玩家是否有建筑权限
            if (!player.hasPermission(BUILD_PERMISSION)) {
                // 检查是否踩踏了农田
                if (event.getBlock().getType() == Material.SOIL) {
                    event.setCancelled(true);
                    //player.sendMessage("§c你没有建筑权限喵~");
                }
            }
        }
    }
}