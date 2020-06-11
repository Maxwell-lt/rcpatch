package maxwell_lt.rcpatch;

import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions({"maxwell_lt.rcpatch"})
public class RailcraftPatchPlugin implements IFMLLoadingPlugin, IFMLCallHook {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{RailcraftPatchClassTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return RailcraftPatchDummyContainer.class.getName();
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return RailcraftPatchPlugin.class.getName();
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public Void call() throws Exception {
        return null;
    }
}
