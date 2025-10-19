package nekoserver;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveEntitiesCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 检查玩家是否有权限
        if (!sender.hasPermission("nekospawn.removeentities")) {
            sender.sendMessage("§c你没有权限使用此命令！");
            return true;
        }
        
        // 移除所有生物实体
        int count = EntityManager.removeAllEntities();
        
        // 发送结果消息
        sender.sendMessage("§a已成功移除 " + count + " 个生物实体！");
        
        return true;
    }
}