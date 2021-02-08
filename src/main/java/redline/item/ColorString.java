package redline.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.client.Minecraft;
//彩色字体
@SideOnly(Side.CLIENT)
public class ColorString {
    private static final EnumChatFormatting[] morecolor;
    private static final EnumChatFormatting[] colour;
    private static final EnumChatFormatting[] makestatic;
    public ColorString(){

    }
    public static String makeMoreColor(String input) {
        return formatting(input, morecolor, 50.0D);
    }
    public static String makeColour(String input) {
        return formatting(input, colour, 80.0D);
    }
    public static String makeStatic(String input) {
        return ludicrousFormatting(input, makestatic, 80.0D, 1, 1);
    }
    public static String formatting(String input, EnumChatFormatting[] colours, double delay) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0.0D) {
            delay = 0.001D;
        }

        int offset = (int)Math.floor((double)(System.currentTimeMillis() & 16383L) / delay) % colours.length;

        for(int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            int col = (i + colours.length - offset) % colours.length;
            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }
    public static String ludicrousFormatting(String input, EnumChatFormatting[] colours, double delay, int step, int posstep) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0.0D) {
            delay = 0.001D;
        }

        int offset = (int)Math.floor((double) Minecraft.getSystemTime() / delay) % colours.length;

        for(int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            int col = (i * posstep + colours.length - offset) % colours.length;
            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }

    static {
        morecolor = new EnumChatFormatting[]{EnumChatFormatting.RED, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.BLUE, EnumChatFormatting.DARK_BLUE, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.WHITE, EnumChatFormatting.BLACK, EnumChatFormatting.OBFUSCATED,EnumChatFormatting.RED, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.BLUE, EnumChatFormatting.DARK_BLUE, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.WHITE, EnumChatFormatting.BLACK, EnumChatFormatting.OBFUSCATED,EnumChatFormatting.RED, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.BLUE, EnumChatFormatting.DARK_BLUE, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.WHITE, EnumChatFormatting.BLACK, EnumChatFormatting.OBFUSCATED};
        colour = new EnumChatFormatting[]{EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.DARK_AQUA, EnumChatFormatting.DARK_BLUE, EnumChatFormatting.DARK_GRAY, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.LIGHT_PURPLE};
        makestatic = new EnumChatFormatting[]{EnumChatFormatting.RED, EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE};
    }
}
