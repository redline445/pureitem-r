package redline.Tab;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;

public class TabsLoader {
    public static CreativeTabs pureTab;

    public TabsLoader(FMLPreInitializationEvent event) {
       pureTab = new RedLineCreativeTabs();
    }
}
