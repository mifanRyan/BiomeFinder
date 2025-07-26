package com.example.biomefinder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BiomeFinder extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("生物群系查找插件已启用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("生物群系查找插件已禁用！");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("biomefinder")) {
            // 检查权限
            if (!sender.hasPermission("biomefinder.use")) {
                sender.sendMessage(ChatColor.RED + "你没有权限使用此命令！");
                return true;
            }

            // 验证
            if (args.length != 3) {
                sender.sendMessage(ChatColor.RED + "用法: /biomefinder <种子> <x> <z>");
                return true;
            }

            try {
                long seed = Long.parseLong(args[0]);
                int x = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);

                // 获取生物群系
                Biome biome = BiomeUtil.getBiomeAt(seed, x, z);
                
                String biomeName = translateBiomeName(biome.name());
                
                sender.sendMessage(ChatColor.GOLD + "========== 生物群系信息 ==========");
                sender.sendMessage(ChatColor.GREEN + "种子: " + ChatColor.WHITE + seed);
                sender.sendMessage(ChatColor.GREEN + "坐标: " + ChatColor.WHITE + "X=" + x + ", Z=" + z);
                sender.sendMessage(ChatColor.GREEN + "生物群系: " + ChatColor.AQUA + biomeName);
                sender.sendMessage(ChatColor.GOLD + "==============================");
                
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "错误: 种子和坐标必须是数字！");
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "发生错误: " + e.getMessage());
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    // 翻译
    private String translateBiomeName(String biomeName) {
        switch (biomeName) {
            case "PLAINS": return "平原";
            case "FOREST": return "森林";
            case "MOUNTAINS": return "山地";
            case "SWAMP": return "沼泽";
            case "RIVER": return "河流";
            case "OCEAN": return "海洋";
            case "DESERT": return "沙漠";
            case "TAIGA": return "针叶林";
            case "JUNGLE": return "丛林";
            case "SAVANNA": return "热带草原";
            case "SNOWY_TUNDRA": return "积雪的冻原";
            case "ICE_SPIKES": return "冰刺之地";
            case "BEACH": return "海滩";
            case "MUSHROOM_FIELDS": return "蘑菇岛";
            case "BADLANDS": return "恶地";
            case "DEEP_OCEAN": return "深海";
            case "BIRCH_FOREST": return "桦木森林";
            case "DARK_FOREST": return "黑森林";
            case "SAVANNA_PLATEAU": return "热带高原";
            case "WOODED_MOUNTAINS": return "繁茂的山地";
            case "SNOWY_TAIGA": return "积雪的针叶林";
            case "GIANT_TREE_TAIGA": return "巨型针叶林";
            case "FLOWER_FOREST": return "繁花森林";
            case "DESERT_LAKES": return "沙漠湖泊";
            case "SWAMP_HILLS": return "沼泽丘陵";
            case "SNOWY_BEACH": return "积雪的海滩";
            case "FROZEN_OCEAN": return "冻洋";
            case "FROZEN_RIVER": return "冻河";
            case "SNOWY_MOUNTAINS": return "雪山";
            case "STONE_SHORE": return "石岸";
            case "SUNFLOWER_PLAINS": return "向日葵平原";
            case "DESERT_HILLS": return "沙漠丘陵";
            case "TAIGA_HILLS": return "针叶林丘陵";
            case "MOUNTAIN_EDGE": return "山地边缘";
            case "JUNGLE_HILLS": return "丛林丘陵";
            case "BIRCH_FOREST_HILLS": return "桦木森林丘陵";
            case "BADLANDS_PLATEAU": return "恶地高原";
            case "DEEP_WARM_OCEAN": return "温暖的深海";
            case "LUKEWARM_OCEAN": return "温和的海洋";
            case "COLD_OCEAN": return "寒冷的海洋";
            case "DEEP_COLD_OCEAN": return "寒冷的深海";
            case "DEEP_FROZEN_OCEAN": return "冰冻的深海";
            case "WARM_OCEAN": return "温暖的海洋";
            case "MODIFIED_JUNGLE": return "变种丛林";
            case "MODIFIED_JUNGLE_EDGE": return "变种丛林边缘";
            case "TALL_BIRCH_FOREST": return "高大桦木森林";
            case "TALL_BIRCH_HILLS": return "高大桦木丘陵";
            case "DARK_FOREST_HILLS": return "黑森林丘陵";
            case "SNOWY_TAIGA_HILLS": return "积雪的针叶林丘陵";
            case "GIANT_SPRUCE_TAIGA": return "巨型云杉针叶林";
            case "GIANT_SPRUCE_TAIGA_HILLS": return "巨型云杉针叶林丘陵";
            case "MODIFIED_GRAVELLY_MOUNTAINS": return "变种沙砾山地";
            case "SHATTERED_SAVANNA": return "破碎的热带草原";
            case "SHATTERED_SAVANNA_PLATEAU": return "破碎的热带高原";
            case "ERODED_BADLANDS": return "风蚀恶地";
            case "MODIFIED_WOODED_BADLANDS_PLATEAU": return "变种繁茂的恶地高原";
            case "MODIFIED_BADLANDS_PLATEAU": return "变种恶地高原";
            case "BAMBOO_JUNGLE": return "竹林";
            case "BAMBOO_JUNGLE_HILLS": return "竹林丘陵";
            default: return biomeName; // 太多了别的就不翻译了
        }
    }
}