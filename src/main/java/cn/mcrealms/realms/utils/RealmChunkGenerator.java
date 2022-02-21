package cn.mcrealms.realms.utils;


import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class RealmChunkGenerator extends ChunkGenerator {

    public static ChunkGenerator getDefaultWorldGenerator() {
        return new RealmWorldGenerator();
    }

    public static class RealmWorldGenerator extends ChunkGenerator {

        @Override
        public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
            return createChunkData(world);
        }
    }
}
