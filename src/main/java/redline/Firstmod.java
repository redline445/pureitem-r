package redline;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.core.Logger;
import redline.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//mod注册
@Mod(
        modid = "rl.1",
        name = "PureItem",
        version = "1.0"
)
public class Firstmod
{
    public static final String MODID = "rl.1";
    public static final String NAME = "PureItem";
    public static final String VERSION = "1.0";

    @Instance(redline.Firstmod.MODID)
    public static redline.Firstmod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        System.out.print("writer:redline");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
    //调用注册部分（因为我把注册分开写了）
    @SidedProxy(clientSide = "redline.client.ClientProxy",
            serverSide = "redline.common.CommonProxy")
    public static CommonProxy proxy;
}

