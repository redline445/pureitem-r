package redline.attack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
//本mod纯属娱乐
//不要和大佬写的mod比
public class RemoveEntity {
    public static Class Witherupdate;
    public static double Infinity = 1.0D / 0.0;
    public List fastSpawnList = new ArrayList();
    public  void removeEntity(EntityPlayer player) {
        List<Entity> allEntities = new ArrayList(player.worldObj.loadedEntityList);
        List<Entity> items = new ArrayList();
        Iterator var4 = allEntities.iterator();

        //掉落固定物品
        if (fallitem) {
            ItemStack item = new ItemStack(Items.nether_star, 64, 0);
            ItemStack block =  new ItemStack(Blocks.diamond_block, 64, 0);
            ItemStack egg =  new ItemStack(Blocks.dragon_egg, 64, 0);
            ItemStack bedBlock = new ItemStack(Blocks.bedrock, 64, 0);
            ItemStack obsidian = new ItemStack(Blocks.obsidian, 64, 0);
            EntityItem item1=new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,item);
            EntityItem block1=new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,block);
            EntityItem bedBlock1=new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,bedBlock);
            EntityItem egg1=new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,egg);
            EntityItem obsidian1=new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,obsidian);
            item1.onCollideWithPlayer(player);
            block1.onCollideWithPlayer(player);
            bedBlock1.onCollideWithPlayer(player);
            egg1.onCollideWithPlayer(player);
            obsidian1.onCollideWithPlayer(player);
            player.worldObj.spawnEntityInWorld(item1);
            player.worldObj.spawnEntityInWorld(egg1);
            player.worldObj.spawnEntityInWorld(bedBlock1);
            player.worldObj.spawnEntityInWorld(obsidian1);
            player.worldObj.spawnEntityInWorld(block1);
        }
        Item it = null;
        Random rand;
        rand = new Random();
        if (fallitem) {
            Iterator ilist = Item.itemRegistry.iterator();
            int icount = 0;
            while (ilist.hasNext()) {
                it = (Item) ilist.next();
                if (it != null) {
                    ++icount;
                }
            }
            int j = 0;
            int bcount;
            while (j <= 7) {
                bcount = 1 + rand.nextInt(icount);
                for (ilist = Item.itemRegistry.iterator(); bcount > 0 && ilist.hasNext(); --bcount) {
                    it = (Item) ilist.next();
                }
                ++j;
                ItemStack stack=new ItemStack(it,1,0);
                player.addExperienceLevel(32767);
               EntityItem it1=new EntityItem(player.worldObj,player.posX,player.posY,player.posZ,stack);
               it1.onCollideWithPlayer(player);
               player.worldObj.spawnEntityInWorld(it1);
            }
        }
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
                    } while (entity == player);
                    List entityList = new ArrayList();
                    entityList.add(entity);
                    if (entity instanceof Entity){
                        this.remove(entity,player);
                    }
                }
            }
        }
    }
    public void remove(Entity entity,EntityPlayer player) {
        if (entity instanceof Entity && !(entity instanceof EntityItem)) {
            try {
                entity.setUnUpdate = true;
            } catch (Throwable exp) {
            }
            if (entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).setInvisible(true);
                ((EntityLivingBase) entity).setKIll = true;
                ((EntityLivingBase) entity).setTrueDead = true;
            }
            if (entity instanceof EntityPlayer) {
                EntityPlayer player1 = (EntityPlayer) entity;
                player1.clearActivePotions();
                player1.hurtResistantTime = 0;
                player1.attackEntityFrom(DamageSource.outOfWorld.setMagicDamage().setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute(), (float) Infinity);
                player1.setHealth((float) -Infinity);
                player1.onDeath(DamageSource.outOfWorld);
                player1.experienceLevel = (int) -Infinity;
                player1.experience = 0.0F;
                player1.experienceTotal = 0;
                entity.worldObj.setEntityState(entity, (byte) 3);
                player1.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(-Infinity);
                player1.attackEntityFrom(DamageSource.outOfWorld, (float) -Infinity);
                player1.hurtResistantTime = (int) -Infinity;
                player1.isDead = true;
            }
        } else {
            if (entity != null) {
                if (!fallitem) {
                    entity.isDead=true;
                } else{entity.onCollideWithPlayer(player);}
            }
        }
    }
    public static boolean fallitem;
    public static boolean chunkExists(World world, int p_72916_1_, int p_72916_2_) {
        return world.getChunkProvider().chunkExists(p_72916_1_, p_72916_2_);
    }
}
