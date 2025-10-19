package nekoserver;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;

public class MenuGUI {
    private static final int MENU_SIZE = 27; // 3行GUI
    private static final String MENU_TITLE = "§d✧猫咪梦幻菜单✧";
    private static final String BUNGEE_CHANNEL = "BungeeCord";
    private static final int ENTER_GAME_SLOT = 13; // "进入游戏"按钮位置
    
    public static void openMenu(Player player) {
        // 创建3行GUI
        Inventory menu = Bukkit.createInventory(null, MENU_SIZE, MENU_TITLE);
        
        // 创建核心功能按钮
        createEnterGameButton(menu);
        
        // 添加装饰性物品
        addDecorativeItems(menu);
        
        // 打开GUI
        player.openInventory(menu);
    }
    
    /**
     * 创建"进入游戏"按钮
     */
    private static void createEnterGameButton(Inventory menu) {
        ItemStack enterGameItem = new ItemStack(Material.DIAMOND);
        ItemMeta enterGameMeta = enterGameItem.getItemMeta();
        if (enterGameMeta != null) {
            enterGameMeta.setDisplayName("§d✧进入梦幻世界✧");
            enterGameMeta.setLore(Arrays.asList(
                "§7§o可爱的猫娘正在等待你的到来喵~",
                "§7§o点击即可传送到游戏世界喵!",
                "§e§l★ 二次元猫娘专属传送 ★"
            ));
            enterGameItem.setItemMeta(enterGameMeta);
        }
        
        // 将按钮放在中心位置
        menu.setItem(ENTER_GAME_SLOT, enterGameItem);
    }
    
    /**
     * 添加装饰性物品，美化GUI界面
     */
    private static void addDecorativeItems(Inventory menu) {
        // 创建四角装饰物品
        createCornerDecorations(menu);
        
        // 创建中心按钮周围的装饰
        createCenterDecorations(menu);
    }
    
    /**
     * 创建四角装饰物品
     */
    private static void createCornerDecorations(Inventory menu) {
        // 左上角 - 小鱼干
        menu.setItem(0, createDecorativeItem(Material.COOKED_FISH, "§b✧小鱼干✧", "§7§o猫咪最爱的零食喵~"));
        
        // 右上角 - 猫薄荷
        menu.setItem(8, createDecorativeItem(Material.LEAVES, "§a✧猫薄荷✧", "§7§o让猫咪放松的香草喵~"));
        
        // 左下角 - 玩具球
        menu.setItem(18, createDecorativeItem(Material.STRING, "§c✧玩具球✧", "§7§o猫咪的娱乐用品喵~"));
        
        // 右下角 - 猫窝
        menu.setItem(26, createDecorativeItem(Material.BED, "§d✧小猫窝✧", "§7§o猫咪休息的地方喵~"));
    }
    
    /**
     * 创建中心按钮周围的装饰
     */
    private static void createCenterDecorations(Inventory menu) {
        ItemStack starItem = createDecorativeItem(Material.NETHER_STAR, "§e✧星星碎片✧", "§7§o闪闪发光的魔法碎片喵~");
        
        // 在"进入游戏"按钮周围放置装饰物品
        menu.setItem(4, starItem.clone());   // 上方
        menu.setItem(12, starItem.clone());  // 左方
        menu.setItem(14, starItem.clone());  // 右方
        menu.setItem(22, starItem.clone());  // 下方
    }
    
    /**
     * 创建装饰性物品的通用方法
     */
    private static ItemStack createDecorativeItem(Material material, String displayName, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static void handleMenuClick(Player player, int slot) {
        // 检查是否点击了"进入游戏"按钮
        if (slot == ENTER_GAME_SLOT) {
            // 连接到lobby服务器
            connectToLobbyServer(player);
            player.closeInventory();
        }
    }
    
    private static void connectToLobbyServer(Player player) {
        try {
            // 从配置文件获取服务器连接信息
            String serverName = getLobbyServerNameFromConfig();
            
            if (serverName != null && !serverName.isEmpty()) {
                // 使用插件通道发送玩家到其他服务器
                player.sendMessage("§d✧正在连接到梦幻世界: " + serverName + " 喵~✧");
                sendPlayerToServer(player, serverName);
            } else {
                // 如果没有配置服务器名称，发送错误消息
                player.sendMessage("§c服务器配置错误，请联系管理员！喵~");
            }
        } catch (Exception e) {
            // 如果发生任何错误，发送详细的错误消息
            Bukkit.getLogger().severe("[NekoSpawn] 连接服务器时发生未预期错误: " + e.getMessage());
            e.printStackTrace();
            player.sendMessage("§c连接失败，请联系管理员！错误: " + e.getMessage() + " 喵~");
        }
    }
    
    private static void sendPlayerToServer(Player player, String serverName) {
        try {
            // 使用BungeeCord插件通道发送玩家到指定服务器
            // 这种方法适用于BungeeCord和Velocity
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            
            out.writeUTF("Connect");
            out.writeUTF(serverName);
            
            player.sendPluginMessage(NekoSpawn.getPlugin(NekoSpawn.class), BUNGEE_CHANNEL, b.toByteArray());
            Bukkit.getLogger().info("[NekoSpawn] 已发送玩家 " + player.getName() + " 到服务器 " + serverName);
        } catch (Exception e) {
            Bukkit.getLogger().severe("[NekoSpawn] 通过插件通道发送玩家时出错: " + e.getMessage());
            e.printStackTrace();
            
            // 如果插件通道失败，回退到命令方式
            fallbackToCommandMethod(player, serverName);
        }
    }
    
    private static void fallbackToCommandMethod(Player player, String serverName) {
        Bukkit.getLogger().warning("[NekoSpawn] 插件通道方法失败，回退到命令方式");
        
        // 尝试不同的命令格式，确保兼容性
        boolean success = false;
        
        // 首先尝试标准的命令格式（玩家直接执行）
        try {
            player.performCommand("server " + serverName);
            success = true;
            Bukkit.getLogger().info("[NekoSpawn] 尝试使用玩家命令连接到服务器: " + serverName);
        } catch (Exception e) {
            Bukkit.getLogger().warning("[NekoSpawn] 玩家命令连接失败: " + e.getMessage());
        }
        
        // 如果上面的命令失败，尝试使用控制台命令
        if (!success) {
            try {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "server " + player.getName() + " " + serverName);
                success = true;
                Bukkit.getLogger().info("[NekoSpawn] 尝试使用控制台命令连接到服务器: " + serverName);
            } catch (Exception e) {
                Bukkit.getLogger().warning("[NekoSpawn] 控制台命令连接失败: " + e.getMessage());
            }
        }
        
        // 如果所有方法都失败了，发送错误消息
        if (!success) {
            player.sendMessage("§c无法连接到游戏服务器，请联系管理员！喵~");
            Bukkit.getLogger().severe("[NekoSpawn] 无法连接到服务器 " + serverName + "，所有方法都已尝试");
        }
    }
    
    private static String getLobbyServerNameFromConfig() {
        try {
            // 从NekoSpawn插件获取配置
            NekoSpawn plugin = NekoSpawn.getPlugin(NekoSpawn.class);
            if (plugin != null) {
                // 从配置文件获取服务器名称
                String serverName = plugin.getConfig().getString("lobby-server.name", "lobby");
                Bukkit.getLogger().info("[NekoSpawn] 从配置文件读取到服务器名称: " + serverName);
                return serverName;
            } else {
                Bukkit.getLogger().warning("[NekoSpawn] 无法获取插件实例");
            }
        } catch (Exception e) {
            // 如果读取配置出错，记录错误并返回默认值
            Bukkit.getLogger().severe("[NekoSpawn] 读取配置文件时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
        
        return "lobby";
    }
}