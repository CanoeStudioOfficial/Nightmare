package org.canoestudio.nightmare.init;

import org.canoestudio.nightmare.NightmareMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModSounds {
    public static final SoundEvent PHANTOM_AMBIENT = createSoundEvent("phantom_ambient");
    public static final SoundEvent PHANTOM_HURT = createSoundEvent("phantom_hurt");
    public static final SoundEvent PHANTOM_DEATH = createSoundEvent("phantom_death");

    private static SoundEvent createSoundEvent(String name) {
        ResourceLocation location = new ResourceLocation(NightmareMod.MOD_ID, name);
        return new SoundEvent(location).setRegistryName(location);
    }

    public static void registerSounds() {
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(PHANTOM_AMBIENT);
        event.getRegistry().register(PHANTOM_HURT);
        event.getRegistry().register(PHANTOM_DEATH);
    }
}
