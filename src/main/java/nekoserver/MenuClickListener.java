package nekoserver;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener {
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 确保点击者是玩家
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getWhoClicked();
        
        // 检查是否点击了我们的菜单GUI
        if (event.getView().getTitle().equals("§d✧猫咪梦幻菜单✧")) {
            event.setCancelled(true); // 取消默认的点击行为
            
            // 处理菜单点击
            if (event.getSlot() >= 0) {
                MenuGUI.handleMenuClick(player, event.getSlot());
            }
        }
    }
}