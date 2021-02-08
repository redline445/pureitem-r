package redline.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

public class OnProtect {
    private static final List<String> setUnUpdateList=new ArrayList<String>();

    public OnProtect() {
    }

    protected static void OnAdded(String name) {
        setUnUpdateList.add(name);
    }

    public static void addRemove(EntityPlayer player) {
        if (!inRemove(player)) {
            setUnUpdateList.add(player.getDisplayName());
            OnAdded(player.getDisplayName());
        }
    }

    public static boolean inRemove(EntityPlayer player) {
        for(int i = 0; i < setUnUpdateList.size(); ++i) {
            String str = (String)setUnUpdateList.toArray()[i];
            if (str.equals(player.getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    public static List<String> get() {
        return new ArrayList(setUnUpdateList);
    }

    public static boolean IsRemove(EntityPlayer player) {
        return inRemove(player);
    }
}
