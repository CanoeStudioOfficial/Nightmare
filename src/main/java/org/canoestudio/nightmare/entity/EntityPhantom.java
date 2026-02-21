package org.canoestudio.nightmare.entity;

import org.canoestudio.nightmare.init.ModItems;
import org.canoestudio.nightmare.init.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityPhantom extends EntityMob {
    public EntityPhantom(World world) {
        super(world);
        this.setSize(1.0f, 0.5f);
        this.experienceValue = 2;
        this.setNoAI(false);
        this.isImmuneToFire = false;
        this.navigator = new PathNavigateFlying(this, this.world);
        this.moveHelper = new EntityFlyHelper(this);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIWander(this, 1.4, 20) {
            @Override
            protected Vec3d getPosition() {
                double dir_x = EntityPhantom.this.posX + (EntityPhantom.this.getRNG().nextFloat() * 2.0f - 1.0f) * 16.0f;
                double dir_y = EntityPhantom.this.posY + (EntityPhantom.this.getRNG().nextFloat() * 2.0f - 1.0f) * 16.0f;
                double dir_z = EntityPhantom.this.posZ + (EntityPhantom.this.getRNG().nextFloat() * 2.0f - 1.0f) * 16.0f;
                return new Vec3d(dir_x, dir_y, dir_z);
            }
        });
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIPhantomAttack(this));
        this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.2, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true, true));
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget<>(this, EntityPlayerMP.class, true, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.0);
    }

    @Override
    public net.minecraft.entity.EnumCreatureAttribute getCreatureAttribute() {
        return net.minecraft.entity.EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.PHANTOM_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.PHANTOM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.PHANTOM_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return source != DamageSource.FALL && super.attackEntityFrom(source, amount);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.world.isRemote) {
            if (this.rand.nextFloat() < 0.35f) {
                this.entityDropItem(new ItemStack(ModItems.PHANTOM_FINS), 0.5f);
            }
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData retval = super.onInitialSpawn(difficulty, livingdata);
        this.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 29, 1, false, false));
        return retval;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.setNoGravity(true);
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.state.IBlockState state, net.minecraft.util.math.BlockPos pos) {
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    static class EntityAIPhantomAttack extends EntityAIBase {
        private final EntityPhantom phantom;

        public EntityAIPhantomAttack(EntityPhantom phantom) {
            this.phantom = phantom;
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            return phantom.getAttackTarget() != null && !phantom.getMoveHelper().isUpdating();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return phantom.getMoveHelper().isUpdating() && phantom.getAttackTarget() != null && phantom.getAttackTarget().isEntityAlive();
        }

        @Override
        public void startExecuting() {
            EntityLivingBase target = phantom.getAttackTarget();
            if (target != null) {
                Vec3d vec3d = target.getPositionEyes(1.0f);
                phantom.getMoveHelper().setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.4);
            }
        }

        @Override
        public void updateTask() {
            EntityLivingBase target = phantom.getAttackTarget();
            if (target != null) {
                double distance = phantom.getDistanceSq(target);
                if (distance <= getAttackReachSq(target)) {
                    phantom.attackEntityAsMob(target);
                } else if (distance < 16.0) {
                    Vec3d vec3d = target.getPositionEyes(1.0f);
                    phantom.getMoveHelper().setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.4);
                }
            }
        }

        protected double getAttackReachSq(EntityLivingBase attackTarget) {
            return phantom.width * 1.5 * phantom.height * 1.5 + attackTarget.height;
        }
    }
}
