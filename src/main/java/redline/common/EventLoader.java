package redline.common;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import redline.attack.RemoveEntity;
import redline.item.ItemLoader;
import redline.item.OnProtect;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//事件
public class EventLoader {
    public static boolean getMinecraftDoesPauseGame() {
        if (timeStop) {
            System.out.println("fuck time fffffff");
            return true;
        } else {
            Minecraft mc=Minecraft.getMinecraft();
            return mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic();
        }
    }

    //注册事件
    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
    }
public static boolean fastTime;
    public static double inf = 1.0D / 0.0;
    //伤害事件
    @SubscribeEvent
    public void onGetHurt(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (ItemLoader.isInfinite(player) || ItemLoader.isGod(player)) {
                protect(player);
                player.deathTime = 0;
                player.isDead = false;
                event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void onAttacked(LivingAttackEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (ItemLoader.isInfinite(player) || ItemLoader.isGod(player)) {
                protect(player);
                player.deathTime = 0;
                player.isDead = false;
                event.setCanceled(true);
            }
        }
    }

    //死亡事件
    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (ItemLoader.isInfinite(player) || ItemLoader.isGod(player)) {
                player.deathTime = 0;
                player.isDead = false;
                event.setCanceled(true);
            }
        }
    }

    //实体更新事件
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if ((event.entityLiving instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.entity;
            if (ItemLoader.isGod(player)) {
                int i;
                RemoveEntity removeEntity=new RemoveEntity();
                for (i = 0; i < removeEntity.fastSpawnList.size(); ++i) {
                    Entity entity1 = (Entity) removeEntity.fastSpawnList.get(i);
                    System.out.println(entity1.getClass().getName());
                    entity1.isDead=true;
                }
                player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 1, 1, true));
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 0, 5, true));
                player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 0, 99999999, true));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 0, 2, true));
                protect(player);
                OnProtect.addRemove(player);
               if (!player.inventory.hasItem(ItemLoader.GodPower)&&OnProtect.inRemove(player)){
                   ItemStack itemStack=new ItemStack(ItemLoader.GodPower);
                   player.inventory.addItemStackToInventory(itemStack);
               }
            }
        }
    }
    private void addDrop(LivingDropsEvent event, ItemStack drop) {
        EntityItem entityItem = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, drop);
        event.drops.add(entityItem);
    }

    private static Random random = new Random();

    //玩家操控事件
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        //玩家左键方块
        if (event.face != -1 && !event.world.isRemote && event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && event.entityPlayer.getHeldItem() != null) {
            //获得方块
            Block block = event.world.getBlock(event.x, event.y, event.z);
            int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
            //判断玩家手持物品即是否为技术型方块
            if (block.getPlayerRelativeBlockHardness(event.entityPlayer, event.entityPlayer.worldObj, event.x, event.y, event.z) <= 0.0F && (event.entityPlayer.getHeldItem().getItem() == ItemLoader.strangePickaxe ) && event.entityPlayer.getHeldItem().getItem().canHarvestBlock(Blocks.stone, event.entityPlayer.getHeldItem())) {
                if (block.quantityDropped(random) == 0) {
                    ItemStack drop = block.getPickBlock(this.raytraceFromEntity(event.world, event.entityPlayer, true, 10.0D), event.world, event.x, event.y, event.z, event.entityPlayer);
                    if (drop == null) {
                        drop = new ItemStack(block, 1, meta);
                    }
                    //掉落方块
                    this.dropItem(drop, event.entityPlayer.worldObj, event.x, event.y, event.z);
                } else {
                    block.harvestBlock(event.world, event.entityPlayer, event.x, event.y, event.z, meta);
                }
                //方块设置为空气
                event.entityPlayer.worldObj.setBlockToAir(event.x, event.y, event.z);
                event.world.playAuxSFX(2001, event.x, event.y, event.z, Block.getIdFromBlock(block) + (meta << 12));
                event.entityPlayer.getHeldItem().getItem().onBlockDestroyed(event.entityPlayer.getHeldItem(), event.world, block, event.x, event.y, event.z, event.entityPlayer);
            }
        }
    }

    //掉落方块部分
    private void dropItem(ItemStack drop, World world, int x, int y, int z) {
        float f = 0.7F;
        double d0 = (double) (random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        double d1 = (double) (random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        double d2 = (double) (random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, drop);
        entityitem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityitem);
    }

    //方块的破坏动画
    private MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean wut, double range) {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationPitch - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;
        if (!world.isRemote && player instanceof EntityPlayer) {
            ++d1;
        }

        double d2 = player.prevPosX + (player.prevPosZ - player.prevPosX) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector((double) f7 * range, (double) f6 * range, (double) f8 * range);
        return world.rayTraceBlocks(vec3, vec31, wut);
    }
    public void protect(EntityPlayer player) {
        player.capabilities.disableDamage = true;
        player.capabilities.isCreativeMode = true;
        player.capabilities.allowFlying = true;
        player.setInvisible(false);
        player.capabilities.allowEdit = true;
        player.getFoodStats().addStats(20, 20.0F);
        player.isDead = false;
        player.extinguish();
        player.deathTime = 0;
        player.getDataWatcher().updateObject(6, MathHelper.clamp_float(20.0F, 0.0F, player.getMaxHealth()));
        player.maxHurtTime = (int) inf;
        player.prevHealth = 20.0F;
        player.maxHurtResistantTime = (int) inf;
        player.velocityChanged = false;
        if (player.posY < -45.0D) {
            player.motionY += 10.0D;
        }
        if (player.posY > 450000.0D) {
            player.motionY -= 10000.0D;
        }
    }
    public static boolean isUsingUS;
    public static boolean timeStop;
}