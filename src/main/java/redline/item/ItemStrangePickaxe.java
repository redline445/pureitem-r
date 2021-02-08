package redline.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import redline.Tab.TabsLoader;
import redline.attack.KillEntity;

import java.util.List;
import java.util.Set;

public class ItemStrangePickaxe extends ItemTool {
    private static final ToolMaterial pure = EnumHelper.addToolMaterial("pure_pickaxe", 2147483647, 2147483647, 3.4028235E38F, GodPower.inf, 2147483647);

    //物品
    public ItemStrangePickaxe() {
        super(0.0F, pure, (Set) null);
        this.setTextureName("redline:pure_pickaxe");
        this.setCreativeTab(TabsLoader.pureTab);
        this.setMaxDamage(0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        ItemStack pick = new ItemStack(this);
        pick.addEnchantment(Enchantment.fortune, 100);
        pick.addEnchantment(Enchantment.sharpness, 100);
        pick.addEnchantment(Enchantment.looting, 100);
        list.add(pick);
    }

    //可以破坏方块
    public boolean canHarvestBlock(Block block, ItemStack itemStack) {
        return true;
    }

    //添加nbt
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
        return itemStack.stackTagCompound.getInteger("DigRange");
    }

    public static void setRange(ItemStack itemStack, int range) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }

        itemStack.stackTagCompound.setInteger("DigRange", range);
    }

    //物品右键时
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (getRange(itemStack) >= 9) {
                setRange(itemStack, 1);
                player.addChatMessage(new ChatComponentTranslation("chat.redline.mod", "1x1"));
            } else {
                setRange(itemStack, getRange(itemStack) + 2);
                player.addChatComponentMessage(new ChatComponentTranslation("chat.redline.mod", getRange(itemStack) + "x" + getRange(itemStack)));
            }
        }

        return itemStack;
    }

    //当物品破坏方块时，摧毁范围方块，大小由setrange决定
    public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase entityLivingBase) {
        int range = getRange(itemStack);
        int x_start = x - range / 2;
        int y_start = y - range / 2;
        int z_start = z - range / 2;
        if (!entityLivingBase.isSneaking()) {
            for (int i = 0; i < range; ++i) {
                for (int j = 0; j < range; ++j) {
                    for (int k = 0; k < range; ++k) {
                        Block currentBlock = world.getBlock(x_start + i, y_start + j, z_start + k);
                        int blockMeta = world.getBlockMetadata(x_start + i, y_start + j, z_start + k);
                        currentBlock.removedByPlayer(world, (EntityPlayer) entityLivingBase, x_start + i, y_start + j, z_start + k, false);
                        currentBlock.onBlockDestroyedByPlayer(world, x_start + i, y_start + j, z_start + k, blockMeta);
                        currentBlock.harvestBlock(world, (EntityPlayer) entityLivingBase, x_start + i, y_start + j, z_start + k, blockMeta);
                    }
                }
            }
        } else {
            for (int i = 0; i < range; ++i) {
                for (int j = 0; j < range; ++j) {
                    for (int k = 0; k < range; ++k) {
                        Block currentBlock = world.getBlock(x_start + i, y_start + j, z_start + k);
                        currentBlock.removedByPlayer(world, (EntityPlayer) entityLivingBase, x_start + i, y_start + j, z_start + k, false);
                    }
                }
            }
        }
        return true;
    }

    //设置毁坏程度（同等于this.setMaxDamage）
    public void setDamage(ItemStack itemStack, int damage) {
        super.setDamage(itemStack, 0);
    }

    //是否由类似附魔时的动画效果
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int meta) {
        return true;
    }

    //挖掘速度
    public float getDigSpeed(ItemStack itemStack, Block block, int metadata) {
        return this.efficiencyOnProperMaterial;
    }

    //击中实体时
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingBase, EntityLivingBase player) {
        int range = getRange(itemStack);
        //获得范围内实体（范围由range决定，仅限有碰撞箱的实体）
        List list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));
        if (list1 != null && !list1.isEmpty()) {
            for (int i11 = 0; i11 < list1.size(); ++i11) {
                Entity target = (Entity) list1.get(i11);
                if (target instanceof EntityLivingBase) {
                    target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player),GodPower.inf);
                    KillEntity.killE((EntityLivingBase) target);
                }
            }
        }
        return true;
    }
}







