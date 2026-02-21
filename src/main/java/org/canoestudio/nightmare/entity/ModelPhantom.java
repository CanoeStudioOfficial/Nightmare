package org.canoestudio.nightmare.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPhantom extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer body_sub_0;
    private final ModelRenderer head;
    private final ModelRenderer left_wing;
    private final ModelRenderer left_wing_sub_0;
    private final ModelRenderer right_wing;
    private final ModelRenderer right_wing_sub_0;
    private final ModelRenderer right_wing_sub_1;
    private final ModelRenderer right_wing_sub_2;
    private final ModelRenderer tail;
    private final ModelRenderer tail2;

    public ModelPhantom() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.cubeList.add(new ModelBox(this.body, 0, 8, -3.0f, 21.0f, -8.0f, 5, 3, 9, 0.0f, false));

        this.body_sub_0 = new ModelRenderer(this);
        this.body_sub_0.setRotationPoint(-0.5f, -0.8f, -7.5f);
        this.setRotationAngle(this.body_sub_0, -0.0436f, 0.0f, 0.0f);
        this.body.addChild(this.body_sub_0);
        this.body_sub_0.cubeList.add(new ModelBox(this.body_sub_0, 0, 0, -3.5f, 22.9781f, -3.9968f, 7, 3, 5, 0.0f, false));

        this.head = new ModelRenderer(this);
        this.head.setRotationPoint(0.0f, 23.0f, -7.0f);

        this.left_wing = new ModelRenderer(this);
        this.left_wing.setRotationPoint(2.0f, 22.0f, -4.0f);
        this.left_wing.cubeList.add(new ModelBox(this.left_wing, 23, 12, 0.0f, -1.0f, -4.0f, 6, 2, 9, 0.0f, false));

        this.left_wing_sub_0 = new ModelRenderer(this);
        this.left_wing_sub_0.setRotationPoint(-10.0f, -24.0f, -4.0f);
        this.left_wing.addChild(this.left_wing_sub_0);
        this.left_wing_sub_0.cubeList.add(new ModelBox(this.left_wing_sub_0, 16, 24, 16.0f, 23.0f, 0.0f, 13, 1, 9, 0.0f, false));

        this.right_wing = new ModelRenderer(this);
        this.right_wing.setRotationPoint(-3.0f, 22.0f, -4.0f);

        this.right_wing_sub_0 = new ModelRenderer(this);
        this.right_wing_sub_0.setRotationPoint(3.0f, 2.0f, 4.0f);
        this.right_wing.addChild(this.right_wing_sub_0);
        this.right_wing_sub_0.cubeList.add(new ModelBox(this.right_wing_sub_0, 23, 12, -9.0f, -3.0f, -8.0f, 6, 2, 9, 0.0f, true));

        this.right_wing_sub_1 = new ModelRenderer(this);
        this.right_wing_sub_1.setRotationPoint(9.0f, -26.0f, -8.0f);
        this.right_wing_sub_0.addChild(this.right_wing_sub_1);

        this.right_wing_sub_2 = new ModelRenderer(this);
        this.right_wing_sub_2.setRotationPoint(-18.0f, 52.0f, 16.0f);
        this.right_wing_sub_1.addChild(this.right_wing_sub_2);
        this.right_wing_sub_2.cubeList.add(new ModelBox(this.right_wing_sub_2, 16, 24, -13.0f, -29.0f, -16.0f, 13, 1, 9, 0.0f, true));

        this.tail = new ModelRenderer(this);
        this.tail.setRotationPoint(0.0f, -2.0f, 1.0f);
        this.tail.cubeList.add(new ModelBox(this.tail, 3, 20, -2.0f, 23.0f, 0.0f, 3, 2, 6, 0.0f, false));

        this.tail2 = new ModelRenderer(this);
        this.tail2.setRotationPoint(0.0f, -1.5f, 7.0f);
        this.tail2.cubeList.add(new ModelBox(this.tail2, 4, 29, -1.0f, 23.0f, 0.0f, 1, 1, 6, 0.0f, false));
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.body.render(scale);
        this.head.render(scale);
        this.left_wing.render(scale);
        this.right_wing.render(scale);
        this.tail.render(scale);
        this.tail2.render(scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        this.head.rotateAngleY = netHeadYaw / 57.295776f;
        this.head.rotateAngleX = headPitch / 57.295776f;
        this.right_wing.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * limbSwingAmount;
        this.left_wing.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662f) * limbSwingAmount;
    }
}
