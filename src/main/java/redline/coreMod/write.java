package redline.coreMod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import redline.attack.RemoveEntity;
import redline.common.EventLoader;
import redline.item.ItemLoader;
import redline.item.OnProtect;

public class write {
    public static void runGameLoop(Minecraft mc) {
        try {
            mc.isGamePaused = EventLoader.getMinecraftDoesPauseGame();
        } catch (Throwable var3) {
        }
    }
    public static void runTick(Minecraft mc) {
        try {
            int i;
            RemoveEntity removeEntity=new RemoveEntity();
            for (i = 0; i < removeEntity.fastSpawnList.size(); ++i) {
                Entity entity1 = (Entity) removeEntity.fastSpawnList.get(i);
                System.out.println(entity1.getClass().getName());
                entity1.isDead=true;
            }
            mc.isGamePaused = EventLoader.getMinecraftDoesPauseGame();
            if (ItemLoader.isGod(mc.thePlayer)||ItemLoader.isInfinite(mc.thePlayer)) {
                if (EventLoader.timeStop) {
                    mc.theWorld.updateEntity(mc.thePlayer);
                }
            }
    } catch (Exception e) {
    }
}

    public static final float getMaxHealth(EntityLivingBase entityLivingBase) {
        try {
            if (entityLivingBase.setKIll) {
                return 0.0F;
            }
            if (entityLivingBase instanceof EntityPlayer) {
                if (OnProtect.IsRemove((EntityPlayer) entityLivingBase)) {
                    return 20.0F;
                }
            }
        }catch (Throwable throwable){}
        return (float) entityLivingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
    }
    public static final float getHealth(EntityLivingBase entityLivingBase){
        try {
            if (entityLivingBase.setKIll) {
                return 0.0F;
            }
            if (entityLivingBase instanceof EntityPlayer) {
                if (OnProtect.IsRemove((EntityPlayer) entityLivingBase)) {
                    return 20.0F;
                }
            }
        }catch (Throwable throwable){}
        return entityLivingBase.getDataWatcher().getWatchableObjectFloat(6);
    }

    public static void removeEntity(World world, Entity p_72900_1_) {
            if (p_72900_1_.riddenByEntity != null) {
                p_72900_1_.riddenByEntity.mountEntity((Entity)null);
            }
            world.onEntityRemoved(p_72900_1_);
            p_72900_1_.isDead=true;
            p_72900_1_.setUnUpdate=true;
    }
    public static void removePlayerEntityDangerously(World world, Entity p_72973_1_) {
        if (!(p_72973_1_ instanceof EntityPlayer)) {
            p_72973_1_.isDead=true;
            int i = p_72973_1_.chunkCoordX;
            int j = p_72973_1_.chunkCoordZ;
            if (p_72973_1_.addedToChunk && chunkExists(world, i, j)) {
                world.getChunkFromChunkCoords(i, j).removeEntity(p_72973_1_);
            }
            p_72973_1_.setUnUpdate=true;
            world.loadedEntityList.remove(p_72973_1_);
            world.onEntityRemoved(p_72973_1_);
            --p_72973_1_.ticksExisted;
        }
    }
    public static void onEntityRemoved(World world, Entity p_72847_1_) {
            for(int i = 0; i < world.worldAccesses.size(); ++i) {
                ((IWorldAccess)world.worldAccesses.get(i)).onEntityDestroy(p_72847_1_);
            }
    }
    public static boolean onLivingUpdate(EntityLivingBase entity) {
        return MinecraftForge.EVENT_BUS.post(new LivingEvent.LivingUpdateEvent(entity));
    }
    public static void updateEntity(World world,Entity p_72870_1_) {
        Entity entity;
        int i;
        double x;
        double y;
        double z;
        world.updateEntityWithOptionalForce(p_72870_1_, true);
        if (p_72870_1_.setUnUpdate&&p_72870_1_ instanceof EntityLivingBase) {
            try {
                RemoveEntity removeEntity=new RemoveEntity();
                x = p_72870_1_.posX;
                y = p_72870_1_.posY;
                z = p_72870_1_.posZ;
                entity = p_72870_1_;
                p_72870_1_.isDead=true;
                long start = System.nanoTime();
                //检测实体是否高速重生并清除实体
                for (i = 0; i < world.loadedEntityList.size(); ++i) {
                    Entity entity1 = (Entity) world.loadedEntityList.get(i);
                    if (entity1.getClass().getName().equals(entity.getClass().getName())) {
                        if (Math.abs(entity1.posX-x)<=1&&Math.abs(entity1.posY-y)<=1&&Math.abs(entity1.posZ-z)<=1) {
                            if (start - System.nanoTime() <= 200) {
                                System.out.println(entity.getClass().getName());
                                removeEntity.fastSpawnList.add(entity);
                                removeEntity.fastSpawnList.add(entity1);
                                entity1.setUnUpdate = true;
                            }
                        }
                    }
                }
            } catch (Throwable throwable) {
            }
        }else {
            if (p_72870_1_.setUnUpdate){
                try {
                    p_72870_1_.isDead=true;
                }catch (Throwable t){}
            }
        }
    }
    public static boolean chunkExists(World world, int p_72916_1_, int p_72916_2_) {
        return world.getChunkProvider().chunkExists(p_72916_1_, p_72916_2_);
    }
}
