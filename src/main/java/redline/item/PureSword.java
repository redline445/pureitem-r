package redline.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import redline.Tab.TabsLoader;
import redline.attack.KillEntity;

import java.util.List;

public class PureSword extends ItemSword {
    public static final ToolMaterial puresword = EnumHelper.addToolMaterial("God", (int) GodPower.inf, -2147483648, GodPower.inf, GodPower.inf, (int) GodPower.inf);

    public PureSword() {
        super(puresword);
        this.setUnlocalizedName("pureSword");
        this.setCreativeTab(TabsLoader.pureTab);
        this.setTextureName("redline:pure_sword");
        this.setMaxDamage(0);
    }

    private float inf = 1.0f / 0.0f;

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        KillEntity.godKill(p_77659_3_, p_77659_1_);
        return p_77659_1_;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_, int m) {
        return true;
    }

    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            for (int i1 = 0; i1 < 40; ++i1) {
                Vec3 vec3 = entityLiving.getLookVec();
                double dx = vec3.xCoord * (double) i1;
                double dy = (double) entityLiving.getEyeHeight() + vec3.yCoord * (double) i1;
                double dz = vec3.zCoord * (double) i1;
                List list1 = entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(entityLiving, entityLiving.boundingBox.expand(2.0D, 2.0D, 2.0D).offset(dx, dy, dz));
                if (list1 != null && !list1.isEmpty()) {
                    for (int i11 = 0; i11 < list1.size(); ++i11) {
                        Entity entity1 = (Entity) list1.get(i11);
                        Entity target = entity1;
                        if (target instanceof EntityLivingBase) {
                            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) entityLiving),inf);
                            KillEntity.killE((EntityLivingBase) target);
                        }
                    }
                }
            }
        }
        return true;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.block;
    }

    @Override
    public String getItemStackDisplayName(ItemStack p_77653_1_) {
        return ColorString.makeMoreColor("PureSword");
    }
}
