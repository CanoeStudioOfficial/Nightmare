package org.canoestudio.nightmare.init;

import org.canoestudio.nightmare.NightmareMod;
import org.canoestudio.nightmare.item.ItemPhantomMembrane;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    public static final Item PHANTOM_MEMBRANE = new ItemPhantomMembrane();

    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(PHANTOM_MEMBRANE);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(PHANTOM_MEMBRANE, 0, 
            new ModelResourceLocation(PHANTOM_MEMBRANE.getRegistryName(), "inventory"));
    }
}
