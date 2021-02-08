package redline.Tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import redline.item.ItemLoader;

public class RedLineCreativeTabs extends CreativeTabs {
    public RedLineCreativeTabs()
    {
        super("Pure");
    }

    @Override
    public Item getTabIconItem()
    {
        return ItemLoader.GodPower;
    }
}
