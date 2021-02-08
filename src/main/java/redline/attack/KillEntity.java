package redline.attack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;
import redline.item.GodPower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//本mod纯属娱乐
//不要和大佬写的mod比
public class KillEntity {
    public static double inf = 1.0f / 0.0f;

    //获得实体
    public static Entity getEntity(Entity entity) {
        if (entity instanceof EntityDragonPart) {
            return (Entity) ((EntityDragonPart) entity).entityDragonObj;
        } else {
            return entity instanceof EntityLivingBase ? entity : null;
        }
    }

    //杀死实体
    public static void godKill(EntityPlayer player, ItemStack stack) {
        //获得所有以加载的实体
        List<Entity> allEntities = new ArrayList(player.worldObj.loadedEntityList);
        List<Entity> items = new ArrayList();
        Iterator var4 = allEntities.iterator();
        //导出实体
        while (true) {
            Entity entity;
            label68:
            while (true) {
                while (true) {
                    do {
                        do {
                            if (!var4.hasNext()) {
                                if (!player.worldObj.isRemote) {
                                    for (var4 = items.iterator(); var4.hasNext(); entity = (Entity) var4.next()) {
                                    }
                                }
                                return;
                            }
                            entity = (Entity) var4.next();
                        } while (entity == null);
                    } while (entity instanceof EntityPlayer);
                    //判断实体不是掉落物或经验
                    if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                        Entity target = getEntity(entity);
                        if (target == null) {
                            break label68;
                        }
                        if (target instanceof EntityLivingBase) {
                            killE((EntityLivingBase) target);
                        }
                    } else {
                        //如果是掉落物或经验
                        items.add(entity);
                        //让掉落物或经验跟随玩家
                        entity.onCollideWithPlayer(player);
                    }
                }
            }
        }
    }

    public static void killE(EntityLivingBase target) {
        target.attackEntityFrom(DamageSource.outOfWorld, (float) 3.4028235E38F);
        ((EntityLivingBase) target).setHealth(0);
        ((EntityLivingBase) target).setKIll = true;
        for (int q = 1; q <= 50; q++) {
            if (q != 7 && q != 8 && q != 1 & q != 11 && q != 16 && q != 17 && q != 18 && q != 13 && q != 12 && q != 14 && q != 21 && q != 9 && q != 23 && q != 10 && q != 15 && q != 4 && q != 20 && q != 19 && q != 22 && q != 25) {
                try {
                    target.getDataWatcher().updateObject(q, MathHelper.clamp_float(0.0F, 0.0F, target.getMaxHealth()));
                } catch (Throwable e) {
                }
            }
        }
    }
}
