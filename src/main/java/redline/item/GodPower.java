package redline.item;


import com.google.common.collect.Multimap;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import redline.Tab.TabsLoader;
import redline.attack.KillEntity;
import redline.attack.RemoveEntity;
import redline.common.EventLoader;
import redline.entity.ColorLightning;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public  class GodPower extends ItemSword {
    public static float inf = 1.0f / 0.0f;
    public static boolean severTick;
    public static final ToolMaterial pure = EnumHelper.addToolMaterial("God", (int) inf, -2147483648, inf, -4, (int) inf);

    public GodPower() {
        super(pure);
        this.setUnlocalizedName("godpower");
        this.setCreativeTab(TabsLoader.pureTab);
        this.setTextureName("redline:god_power");
        this.setMaxDamage(0);
    }

    //物品右键时
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        RemoveEntity removeEntity = new RemoveEntity();
        removeEntity.removeEntity(player);
        super.onItemRightClick(stack, world, player);
        if (player.isSneaking()) {
            RemoveEntity.fallitem = true;
        } else if (!player.isSneaking()) {
            RemoveEntity.fallitem = false;
        }
        try {
            if (!EventLoader.timeStop) {
                int range = 16;
                int x_start = (int) (player.posX - range / 2);
                int y_start = (int) player.posY;
                int z_start = (int) (player.posZ - range / 2);
                for (int i = 0; i < range; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        for (int k = 0; k < range; ++k) {
                            Block currentBlock = player.worldObj.getBlock(x_start + i, y_start + j, z_start + k);
                            currentBlock.removedByPlayer(player.worldObj, player, x_start + i, y_start + j, z_start + k, false);
                            player.worldObj.setBlockToAir(x_start + i, y_start + j, z_start + k);
                        }
                    }
                }
                List list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));
                if (list1 != null && !list1.isEmpty()) {
                    for (int i11 = 0; i11 < list1.size(); ++i11) {
                        Entity entity1 = (Entity) list1.get(i11);
                        if (entity1 != player) {
                            removeEntity.remove(entity1, player);
                        }
                    }
                }
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }
        return stack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        try {
            if (!EventLoader.timeStop) {
                int range = 16;
                int x_start = (int) (player.posX - range / 2);
                int y_start = (int) player.posY;
                int z_start = (int) (player.posZ - range / 2);
                for (int i = 0; i < range; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        for (int k = 0; k < range; ++k) {
                            Block currentBlock = player.worldObj.getBlock(x_start + i, y_start + j, z_start + k);
                            currentBlock.removedByPlayer(player.worldObj, player, x_start + i, y_start + j, z_start + k, false);
                            player.worldObj.setBlockToAir(x_start + i, y_start + j, z_start + k);
                        }
                    }
                }
                List list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));
                if (list1 != null && !list1.isEmpty()) {
                    for (int i11 = 0; i11 < list1.size(); ++i11) {
                        Entity entity1 = (Entity) list1.get(i11);
                        if (entity1 != player) {
                            RemoveEntity removeEntity = new RemoveEntity();
                            removeEntity.remove(entity1, player);
                        }
                    }
                }
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }
    }

    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int p_77615_4_) {
        if (getRange(stack) >= 2) {
            setRange(stack, 1);
        } else {
            setRange(stack, getRange(stack) + 1);
        }
        if (getRange(stack) == 1) {
            EventLoader.timeStop = true;
        } else if (getRange(stack) == 2) {
            EventLoader.timeStop = false;
            RemoveEntity removeEntity = new RemoveEntity();
            removeEntity.removeEntity(player);
            Random random = new Random();
            for (int i = 0; i < 500; ++i) {
                double angle = random.nextDouble() * 20.0D * 3.141592653589793D;
                double distance = random.nextGaussian() * 100.0D;
                double x = Math.sin(angle) * distance + player.posX;
                double z = Math.cos(angle) * distance + player.posZ;
                ColorLightning bolt = new ColorLightning(world, x, (double) world.getPrecipitationHeight((int) x, (int) z), z);
                world.spawnEntityInWorld(bolt);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int meta) {
        return true;
    }

    //将右键时的物品动画改为弓右键时的物品动画（原本是剑格挡时的物品动画)
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (!entityLiving.worldObj.isRemote) {
            if (entityLiving instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
                if (entityPlayer.getHeldItem().getItem() instanceof GodPower || entityPlayer.getHeldItem().getItem() == ItemLoader.GodPower) {
                    KillEntity.godKill((EntityPlayer) entityLiving, stack);
                }
            }
        }
        return false;
    }

    //添加物品信息
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1) {
        super.addInformation(item, player, list, b1);
        list.add(ColorString.makeMoreColor(StatCollector.translateToLocal("汝已成神")));
        list.add(ColorString.makeMoreColor(StatCollector.translateToLocal("无所不能，无处不在")));
        list.add(StatCollector.translateToLocal("info.GodWrite.0"));
        list.add(ColorString.makeStatic(StatCollector.translateToLocal("info.GodDamange.1")) + ColorString.makeColour(StatCollector.translateToLocal("010010110 ")));
    }

    public String getItemStackDisplayName(ItemStack itemStack) {
        return ColorString.makeStatic("GodPower");
    }

    //取消物品的伤害介绍
    @Deprecated
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 0, 0));
        return multimap;
    }

    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHolding) {
        if (!itemStack.hasTagCompound()) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        if (getRange(itemStack) == 0) {
            setRange(itemStack, 1);
        }
    }

    public static int getRange(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        return itemStack.stackTagCompound.getInteger("uses");
    }

    public static void setRange(ItemStack itemStack, int uses) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }

        itemStack.stackTagCompound.setInteger("uses", uses);
    }
}



