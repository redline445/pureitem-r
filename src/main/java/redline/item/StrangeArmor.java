package redline.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import redline.Tab.TabsLoader;

import java.util.ArrayList;
import java.util.List;

public class StrangeArmor extends ItemArmor {
    public static final ItemArmor.ArmorMaterial strange = EnumHelper.addArmorMaterial("strange", 100000000, new int[]{20, 20, 20, 20}, 2147483647);
    public final int slot;
//盔甲
    public StrangeArmor(int slot) {
        super(strange, 0, slot);
        this.slot = slot;
        setCreativeTab(TabsLoader.pureTab);
        //设置物品注册名和贴图路径（贴图为物品栏，不为外表贴图）
        setUnlocalizedName("strange_" + slot);
        setTextureName("redline:strange_" + slot);
        this.setMaxDamage(0);
    }

//盔甲穿戴时更新
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 1, 1, true));
        player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 0, 5, true));
        player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 0, 99999999, true));
        player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 0, 99999999, true));
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 0, 2, true));
    }

    public static class Handler {
        public static List<String> playersWithHat = new ArrayList<String>();
        public static List<String> playersWithFly = new ArrayList<String>();
        public static List<String> playersWithLeg = new ArrayList<String>();
        public static List<String> playersWithFoot = new ArrayList<String>();

        public Handler() {
        }

        public static boolean playerHasHat(EntityPlayer player) {
            ItemStack armour = player.getCurrentArmor(0);
            return armour != null && armour.getItem() == ItemLoader.h;
        }

        public static boolean playerHasChest(EntityPlayer player) {
            ItemStack armour = player.getCurrentArmor(1);
            return armour != null && armour.getItem() == ItemLoader.c;
        }

        public static boolean playerHasLeg(EntityPlayer player) {
            ItemStack armour = player.getCurrentArmor(2);
            return armour != null && armour.getItem() == ItemLoader.l;
        }

        public static boolean playerHasFoot(EntityPlayer player) {
            ItemStack armour = player.getCurrentArmor(3);
            return armour != null && armour.getItem() == ItemLoader.b;
        }

        public static String playerKey(EntityPlayer player) {
            return player.getGameProfile().getName() + ":" + player.worldObj.isRemote;
        }
    }
//设置外表贴图，2为护腿，1为头盔、胸甲、靴子，详情见其他模组教程
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "redline:textures/models/armor/strange_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }
    public boolean hasEffect(ItemStack itemStack, int meta) {
        return true;
    }
}




