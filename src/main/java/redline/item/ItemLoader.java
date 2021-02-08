package redline.item;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
//注册物品
public class ItemLoader {
    public static Item pureItem=new Pure();
    public static Item strangePickaxe=new ItemStrangePickaxe();
    public static Item GodPower=new GodPower();
    public static ItemArmor h=new StrangeArmor(0);
    public static ItemArmor c=new StrangeArmor(1);
    public static ItemArmor l=new StrangeArmor(2);
    public static ItemArmor b=new StrangeArmor(3);
    public static Item pureSword=new PureSword();
    public static Item brickA=new BrickItemA();
    public static ItemBow pureBow=new PureBow();
    public static Item fire=new Fire();
    public ItemLoader(FMLPreInitializationEvent event)
    {
        register(brickA, "BrickItemA");
        register(pureItem, "pureItem");
        register(strangePickaxe,"godPickaxe");
        register(GodPower,"godpower");
        register(h,"strange_0");
        register(c,"strange_1");
        register(l,"strange_2");
        register(b,"strange_3");
        register(fire,"forgun");
        register(pureSword,"pure_sword");
        register(pureBow,"Firework_Pure_Bow");
    }
    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item,name);
    }
//是否为携带全套盔甲或剑
    public static boolean isInfinite(EntityPlayer player){
        if(player.getEquipmentInSlot(1) == null || player.getEquipmentInSlot(2) == null
                || player.getEquipmentInSlot(3) == null || player.getEquipmentInSlot(4) == null)
            return false;
        if(player.getEquipmentInSlot(1).getItem() == b && player.getEquipmentInSlot(2).getItem() == l
                && player.getEquipmentInSlot(3).getItem() == c && player.getEquipmentInSlot(4).getItem() == h)
            return true;
        else
            return false;
    }
    public static boolean isGod(EntityPlayer entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player= (EntityPlayer) entity;
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemStack = player.inventory.getStackInSlot(i);
                if (ItemLoader.GodPower != null && itemStack != null && (itemStack.getItem() instanceof GodPower || itemStack.getItem() == ItemLoader.GodPower)) {
                    OnProtect.addRemove(player);
                    return true;
                }
            }
            return OnProtect.IsRemove(player);
        }
        return entity != null && entity.getDisplayName().equals("redline");
    }
}

