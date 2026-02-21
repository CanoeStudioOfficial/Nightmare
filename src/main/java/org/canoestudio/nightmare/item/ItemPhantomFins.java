package org.canoestudio.nightmare.item;

import org.canoestudio.nightmare.NightmareMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPhantomFins extends Item {
    public ItemPhantomFins() {
        this.setMaxDamage(0);
        this.setMaxStackSize(64);
        this.setTranslationKey(NightmareMod.MOD_ID + ".phantom_fins");
        this.setRegistryName(NightmareMod.MOD_ID, "phantom_fins");
        this.setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public int getMaxItemUseDuration(net.minecraft.item.ItemStack itemstack) {
        return 0;
    }

    @Override
    public float getDestroySpeed(net.minecraft.item.ItemStack stack, net.minecraft.block.state.IBlockState state) {
        return 1.0f;
    }
}
