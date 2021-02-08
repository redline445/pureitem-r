package redline.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import redline.Tab.RedLineCreativeTabs;
import redline.Tab.TabsLoader;
import redline.entity.BrickA;

public class BrickItemA extends Item {
    public BrickItemA(){
       super();
        this.setUnlocalizedName("BrickItemA");
        this.setCreativeTab(TabsLoader.pureTab);
        this.setTextureName("redline:brickItem_a");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new BrickA(world, player));
        }
        return itemStack;
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int meta) {
        return true;
    }
}
