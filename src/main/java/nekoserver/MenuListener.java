package nekoserver;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        // 检查玩家是否右键点击了时钟
        if (item != null && item.getType() == Material.WATCH && 
            item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&
            item.getItemMeta().getDisplayName().contains("猫咪传送门") &&
            (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            
            // 打开菜单GUI
            MenuGUI.openMenu(player);
            event.setCancelled(true);
        }
    }
}