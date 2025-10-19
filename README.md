# NekoSpawn Plugin

这是一个简单的Minecraft服务器插件，用于设置和管理玩家出生点。

## 功能

- 使用 `/spawn set` 命令设置出生点（需要 `nekospawn.setspawn` 权限）
- 玩家加入服务器时自动传送到出生点
- 位置信息保存在 config.yml 中

## 安装

1. 将编译后的插件JAR文件放入服务器的 `plugins` 文件夹
2. 重启服务器

## 使用方法

1. 玩家需要拥有 `nekospawn.setspawn` 权限才能设置出生点
2. 站在想要设置为出生点的位置
3. 执行命令 `/spawn set`
4. 所有玩家加入服务器时都会被传送到这个位置

## 权限

- `nekospawn.setspawn` - 允许设置出生点（默认为OP）