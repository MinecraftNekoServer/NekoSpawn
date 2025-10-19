package nekoserver;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        
        // 给玩家发放菜单时钟
        giveMenuClock(player);
    }
    
    private void giveMenuClock(Player player) {
        // 创建猫娘主题的菜单物品
        ItemStack clock = new ItemStack(Material.WATCH);
        ItemMeta meta = clock.getItemMeta();
        meta.setDisplayName("§d✧猫咪传送门✧");
        meta.setLore(java.util.Arrays.asList(
            "§7§o可爱的猫娘为你服务喵~",
            "§7§o右键打开梦幻菜单喵!"
        ));
        clock.setItemMeta(meta);
        
        // 给玩家物品栏添加时钟（放在物品栏中间位置）
        player.getInventory().setItem(4, clock);
    }
}