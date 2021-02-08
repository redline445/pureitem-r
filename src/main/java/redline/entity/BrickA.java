package redline.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import redline.attack.KillEntity;
import redline.attack.RemoveEntity;
import redline.item.ItemLoader;

import java.util.List;

public class BrickA extends EntityThrowable {
    public BrickA(World worldIn) {
        super(worldIn);
    }

    public BrickA(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public BrickA(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        if (!this.worldObj.isRemote) {
            AxisAlignedBB axisalignedbb = this.boundingBox.expand(16.0D, 8.0D, 16.0D);
            List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            if (list1 != null && !list1.isEmpty()) {
                for (int i11 = 0; i11 < list1.size(); ++i11) {
                    Entity entity1 = (Entity) list1.get(i11);
                    Entity target = (Entity) list1.get(i11);
                    if (target instanceof EntityLivingBase) {
                        KillEntity.killE((EntityLivingBase) target);
                    }
                }
            }
        }
        this.setDead();
    }
    protected float getGravityVelocity() {
        return 0.013F;
    }
}
