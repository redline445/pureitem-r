package redline.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import redline.Firstmod;
import redline.Tab.RedLineCreativeTabs;
import redline.Tab.TabsLoader;
import redline.crafting.CraftingLoader;
import redline.entity.BrickA;
import redline.entity.ColorLightning;
import redline.entity.FireWorkArrow;
import redline.item.ItemLoader;
import redline.item.StrangeArmor;
//普遍注册
public class CommonProxy {
    private static int nextEntity=0;
    public void preInit(FMLPreInitializationEvent event)
    {
        new TabsLoader(event);
        new ItemLoader(event);
        new EventLoader();
    }

    public void init(FMLInitializationEvent event)
    {
        System.out.println("common init");
        new StrangeArmor.Handler();
        new EventLoader();
        EntityRegistry.registerModEntity(BrickA.class,"BrickA",nextEntity++,Firstmod.instance,64,10,true);
        EntityRegistry.registerModEntity(FireWorkArrow.class,"fireWork",nextEntity++,Firstmod.instance,64,10,true);
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
