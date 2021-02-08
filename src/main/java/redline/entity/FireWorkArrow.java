package redline.entity;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class FireWorkArrow extends EntityThrowable {
    public FireWorkArrow(World worldIn) {
        super(worldIn);
    }

    public FireWorkArrow(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public FireWorkArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        if (!this.worldObj.isRemote) {
            if (movingObjectPosition.entityHit instanceof EntityLivingBase) {
                EntityLivingBase base = (EntityLivingBase) movingObjectPosition.entityHit;
                base.setHealth(base.getHealth()-base.getHealth()*30/100);
            }
        }
        this.setDead();
    }
    protected float getGravityVelocity() {
        return 0.013F;
    }
}
