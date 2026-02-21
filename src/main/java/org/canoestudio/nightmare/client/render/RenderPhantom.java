package org.canoestudio.nightmare.client.render;

import org.canoestudio.nightmare.NightmareMod;
import org.canoestudio.nightmare.entity.EntityPhantom;
import org.canoestudio.nightmare.entity.ModelPhantom;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPhantom extends RenderLiving<EntityPhantom> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(NightmareMod.MOD_ID, "textures/entity/phantom.png");

    public RenderPhantom(RenderManager renderManager) {
        super(renderManager, new ModelPhantom(), 0.4f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPhantom entity) {
        return TEXTURE;
    }
}
