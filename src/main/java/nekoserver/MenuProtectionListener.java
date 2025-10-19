package nekoserver;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuProtectionListener implements Listener {
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 检查是否是玩家点击事件
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        // 检查是否点击了菜单物品（时钟）
        ItemStack cursor = event.getCursor();
        ItemStack current = event.getCurrentItem();
        
        // 检查光标上的物品是否为菜单时钟
        if (cursor != null && cursor.getType() == Material.WATCH && 
            cursor.hasItemMeta() && cursor.getItemMeta().hasDisplayName() && 
            cursor.getItemMeta().getDisplayName().contains("猫咪传送门")) {
            event.setCancelled(true);
            return;
        }
        
        // 检查当前槽位的物品是否为菜单时钟
        if (current != null && current.getType() == Material.WATCH && 
            current.hasItemMeta() && current.getItemMeta().hasDisplayName() && 
            current.getItemMeta().getDisplayName().contains("猫咪传送门")) {
            event.setCancelled(true);
            return;
        }
    }
    
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        // 防止丢弃菜单物品
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.getType() == Material.WATCH && 
            item.hasItemMeta() && item.getItemMeta().hasDisplayName() && 
            item.getItemMeta().getDisplayName().contains("猫咪传送门")) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // 玩家重生后重新给予菜单物品
        Player player = event.getPlayer();
        
        // 延迟一点时间给予物品，确保玩家已经完全重生
        player.getServer().getScheduler().runTaskLater(
            player.getServer().getPluginManager().getPlugin("NekoSpawn"), 
            () -> {
                // 检查玩家是否已经有菜单物品
                boolean hasMenu = false;
                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null && item.getType() == Material.WATCH && 
                        item.hasItemMeta() && item.getItemMeta().hasDisplayName() && 
                        item.getItemMeta().getDisplayName().contains("猫咪传送门")) {
                        hasMenu = true;
                        break;
                    }
                }
                
                // 如果没有菜单物品，则给予一个
                if (!hasMenu) {
                    ItemStack clock = new ItemStack(Material.WATCH);
                    ItemMeta meta = clock.getItemMeta();
                    meta.setDisplayName("§d✧猫咪传送门✧");
                    meta.setLore(java.util.Arrays.asList(
                        "§7§o可爱的猫娘为你服务喵~",
                        "§7§o右键打开梦幻菜单喵!",
                        "§c§l不可丢弃 & 不可移动"
                    ));
                    clock.setItemMeta(meta);
                    player.getInventory().setItem(4, clock);
                }
            }, 
            10L // 延迟10 ticks
        );
    }
}