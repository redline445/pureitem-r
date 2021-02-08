package redline.coreMod;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class coreLoader implements IFMLLoadingPlugin {
    public static boolean debug;
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"redline.coreMod.ClassTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "redline.coreMod.core";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        debug = !(Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}