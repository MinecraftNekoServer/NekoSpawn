package nekoserver;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DecorativeItems {
    
    public static ItemStack createCatFood() {
        ItemStack item = new ItemStack(Material.COOKIE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§e✧猫娘的零食✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~这是给猫娘的美味零食!",
                "§7§o仅供观赏，不可食用喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static ItemStack createFish() {
        ItemStack item = new ItemStack(Material.COOKED_FISH);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§b✧猫娘的小鱼干✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~这是猫娘最爱的小鱼干!",
                "§7§o收藏价值极高喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static ItemStack createCatnip() {
        ItemStack item = new ItemStack(Material.LEAVES);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§a✧猫娘的猫薄荷✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~香气扑鼻的猫薄荷!",
                "§7§o让猫娘心情愉悦喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static ItemStack createToy() {
        ItemStack item = new ItemStack(Material.STRING);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§c✧猫娘的玩具球✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~彩色的玩具球!",
                "§7§o让猫娘快乐玩耍喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static ItemStack createBed() {
        ItemStack item = new ItemStack(Material.BED);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§d✧猫娘的小窝✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~舒适的小窝!",
                "§7§o猫娘的休息圣地喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static ItemStack createRibbon() {
        ItemStack item = new ItemStack(Material.WOOL);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§d✧猫娘的蝴蝶结✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~可爱的粉色蝴蝶结!",
                "§7§o猫娘的时尚配饰喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    public static ItemStack createBell() {
        ItemStack item = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§e✧猫娘的铃铛✧");
            meta.setLore(Arrays.asList(
                "§7§o喵~清脆悦耳的铃铛声!",
                "§7§o猫娘的专属饰品喵~"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }
}