package redline.Copy;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import redline.item.ItemLoader;
//添加一个新的合成配方使pureitem可以复制物品
public class CopyItem implements IRecipe {
    public CopyItem() {
    }

    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        int source = 0;
        int item = 0;
        int noitem = 0;

        for(int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {
            ItemStack itemStack = inventoryCrafting.getStackInSlot(i);
            if (itemStack != null && itemStack.getItem() == ItemLoader.pureItem && source == 0) {
                ++source;
            } else if (itemStack != null) {
                ++item;
            }

            if (itemStack == null) {
                ++noitem;
            }
        }

        if (inventoryCrafting.getSizeInventory() == 9 && source == 1 && item == 1 && noitem == 7) {
            return true;
        } else if (inventoryCrafting.getSizeInventory() == 4 && source == 1 && item == 1 && noitem == 2) {
            return true;
        } else {
            return false;
        }
    }

    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        ItemStack itemStack = null;
        int source = 0;

        for(int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {
            ItemStack itemStack1 = inventoryCrafting.getStackInSlot(i);
            if (itemStack1 != null && itemStack1.getItem() != ItemLoader.pureItem) {
                itemStack = itemStack1;
            }

            if (itemStack1 != null && itemStack1.getItem() == ItemLoader.pureItem) {
                ++source;
            }
        }

        ItemStack result;
        if (source == 2) {
            result = new ItemStack(ItemLoader.pureItem);
            result.stackSize = 64;
            return result;
        } else if (itemStack == null) {
            return null;
        } else {
            result = itemStack.copy();
            result.stackSize = 64;
            return result;
        }
    }

    public int getRecipeSize() {
        return 10;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}
