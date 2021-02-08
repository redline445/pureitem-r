package redline.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.RecipeSorter;
import redline.Copy.CopyItem;
import redline.item.ItemLoader;
//合成配方
public class CraftingLoader {
    public CraftingLoader()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.pureItem), new Object[]
                {
                        "   ", " * ", "   ", '*', Blocks.dirt
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.strangePickaxe), new Object[]
                {
                        " ##", "#* ", " * ", '#', ItemLoader.pureItem, '*', Blocks.cobblestone
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.pureSword), new Object[]
                {
                        " # ", " # ", " * ", '#',ItemLoader.pureItem, '*', Blocks.dirt
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.h), new Object[]
                {
                        "###", "   ", "  ", '#',ItemLoader.pureItem,
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.c), new Object[]
                {
                        "# #", " # ", "   ", '#',ItemLoader.pureItem,
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.l), new Object[]
                {
                        " # ", "   ", "# #", '#',ItemLoader.pureItem,
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.b), new Object[]
                {
                        "   ", "   ", "# #", '#',ItemLoader.pureItem,
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.GodPower),new Object[]
                {
                        " *#", "*!*", "@* ",'#',ItemLoader.pureSword,'*',ItemLoader.brickA,'@', ItemLoader.strangePickaxe,'!',ItemLoader.pureItem
                });
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.brickA),new Object[]
                {
                        " * ", "*!*", " * ",'*',Blocks.bedrock,'!',ItemLoader.pureItem,
                });
        RecipeSorter.register("pure", CopyItem.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        GameRegistry.addRecipe(new CopyItem());
    }


    private static void registerSmelting()
    {
    }

    private static void registerFuel()
    {

    }
}
