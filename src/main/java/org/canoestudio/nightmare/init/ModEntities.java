package org.canoestudio.nightmare.init;

import org.canoestudio.nightmare.NightmareMod;
import org.canoestudio.nightmare.entity.EntityPhantom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModEntities {
    private static int entityID = 0;

    public static void registerEntities() {
        registerEntity("phantom", EntityPhantom.class, 64, 3, true, 0x26A7A7, 0x3F2F35);
    }

    public static void registerSpawns() {
        List<Biome> spawnBiomes = new ArrayList<>();
        spawnBiomes.add(Biomes.OCEAN);
        spawnBiomes.add(Biomes.PLAINS);
        spawnBiomes.add(Biomes.DESERT);
        spawnBiomes.add(Biomes.EXTREME_HILLS);
        spawnBiomes.add(Biomes.FOREST);
        spawnBiomes.add(Biomes.TAIGA);
        spawnBiomes.add(Biomes.SWAMPLAND);
        spawnBiomes.add(Biomes.RIVER);
        spawnBiomes.add(Biomes.BEACH);
        spawnBiomes.add(Biomes.SKY);

        EntityRegistry.addSpawn(EntityPhantom.class, 3, 1, 1, EnumCreatureType.MONSTER, spawnBiomes.toArray(new Biome[0]));
    }

    private static void registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
        ResourceLocation registryName = new ResourceLocation(NightmareMod.MOD_ID, name);
        EntityRegistry.registerModEntity(registryName, entityClass, name, entityID++, NightmareMod.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
    }
}
