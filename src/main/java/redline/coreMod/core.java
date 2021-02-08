package redline.coreMod;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Map;

public class core extends DummyModContainer  {
    private boolean enabled = true;
    private Logger log;
    public core(){
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "11111";
        meta.name = "11111";
        meta.version = "1.0.0";
        meta.authorList = Arrays.asList("redline");
        meta.description = "1111";
    }
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}
