package redline.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import redline.common.CommonProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import redline.common.EventLoader;
import redline.crafting.CraftingLoader;
import redline.entity.BrickA;
import redline.entity.ColorLightning;
import redline.entity.FireWorkArrow;
import redline.item.ItemLoader;
import redline.render.ColorRender;
import redline.render.RenderBrickA;
import redline.render.RenderFireWork;

//客户端注册
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
    super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        System.out.println("client init");
        super.init(event);
        new KeyLoader();
        new CraftingLoader();
        RenderingRegistry.registerEntityRenderingHandler(ColorLightning.class, new ColorRender());
        RenderingRegistry.registerEntityRenderingHandler(BrickA.class, new RenderBrickA());
        RenderingRegistry.registerEntityRenderingHandler(FireWorkArrow.class, new RenderFireWork());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

}
