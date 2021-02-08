package redline.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import redline.Tab.TabsLoader;
import redline.entity.FireWorkArrow;

public class PureBow extends ItemBow {
    public PureBow()
    {
        this.setUnlocalizedName("PureBow");
        this.setTextureName("redline:pure_bow");
        this.setMaxDamage(0);
        this.setCreativeTab(TabsLoader.pureTab);
    }
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.bow;
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int meta) {
        return true;
    }
    @Override
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {
            FireWorkArrow entityarrow = new FireWorkArrow(p_77615_2_);
            if (!p_77615_2_.isRemote)
            {
                p_77615_2_.spawnEntityInWorld(entityarrow);
            }
    }
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        return p_77654_1_;
    }
}
