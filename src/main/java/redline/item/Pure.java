package redline.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import redline.Firstmod;
import redline.Tab.TabsLoader;

import java.util.List;

public class Pure extends Item{
    public Pure()
    {
       super();
       this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("pureItem");
        this.setCreativeTab(TabsLoader.pureTab);
        this.setTextureName("redline:pure_Item");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World p_77659_2_, EntityPlayer player) {
        if (player.isSneaking()) {

        }
        return itemStack;
    }
//添加物品介绍
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(ColorString.makeColour("一个像砧板之尘的东西"));
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }
}
