package org.canoestudio.nightmare;


import com.canoestudio.nightmare.Tags;
import org.canoestudio.nightmare.init.ModEntities;
import org.canoestudio.nightmare.init.ModItems;
import org.canoestudio.nightmare.init.ModSounds;
import org.canoestudio.nightmare.proxy.IProxy;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class NightmareMod {
    public static final String MOD_ID = Tags.MOD_ID;


    @SidedProxy(clientSide = "org.canoestudio.nightmare.proxy.ClientProxy", serverSide = "org.canoestudio.nightmare.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.Instance(MOD_ID)
    public static NightmareMod instance;

    public NightmareMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModEntities.registerEntities();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.registerSpawns();
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event);
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        ModSounds.registerSoundEvents(event);
    }
}
