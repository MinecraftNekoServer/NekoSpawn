package nekoserver;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final SpawnManager spawnManager;

    public SetSpawnCommand(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 检查命令发送者是否为玩家
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c此命令只能由玩家执行！");
            return true;
        }

        Player player = (Player) sender;
        
        // 检查玩家是否有权限
        if (!player.hasPermission("nekospawn.setspawn")) {
            player.sendMessage("§c你没有权限使用此命令！");
            return true;
        }

        // 设置出生点
        if (spawnManager.setSpawn(player)) {
            player.sendMessage("§a出生点已成功设置在你的当前位置！");
        } else {
            player.sendMessage("§c设置出生点失败！");
        }

        return true;
    }
}