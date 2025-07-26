package com.example.biomefinder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;

public class BiomeUtil {

    // 获取指定种子和坐标的生物群系
    public static Biome getBiomeAt(long seed, int x, int z) {
        // 创建一个临时世界
        WorldCreator wc = new WorldCreator("temp_biome_world");
        wc.seed(seed);
        wc.environment(World.Environment.NORMAL);
        wc.generateStructures(false);
        wc.generatorSettings("{\"layers\":[{\"block\":\"minecraft:bedrock\",\"height\":1},{\"block\":\"minecraft:dirt\",\"height\":3},{\"block\":\"minecraft:grass_block\",\"height\":1}],\"biome\":\"plains\"}");

        World world = Bukkit.createWorld(wc);
        if (world == null) {
            throw new RuntimeException("无法创建临时世界！");
        }

        // 获取生物群系
        Biome biome = world.getBiome(x, 64, z);
        
        // 卸载临时世界防止占用资源
        Bukkit.unloadWorld(world, false);
        
        return biome;
    }
}