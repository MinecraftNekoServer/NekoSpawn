package nekoserver;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleSpawningCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 检查玩家是否有权限
        if (!sender.hasPermission("nekospawn.togglespawning")) {
            sender.sendMessage("§c你没有权限使用此命令！");
            return true;
        }
        
        // 检查参数
        if (args.length != 1) {
            sender.sendMessage("§c用法: /togglespawning <on|off>");
            return true;
        }
        
        // 根据参数设置生物生成状态
        if (args[0].equalsIgnoreCase("on")) {
            EntityManager.setPreventSpawning(false);
            sender.sendMessage("§a已允许生物生成！");
        } else if (args[0].equalsIgnoreCase("off")) {
            EntityManager.setPreventSpawning(true);
            sender.sendMessage("§a已阻止生物生成！");
        } else {
            sender.sendMessage("§c用法: /togglespawning <on|off>");
            return true;
        }
        
        return true;
    }
}