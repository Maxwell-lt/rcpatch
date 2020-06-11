package maxwell_lt.rcpatch;

import net.minecraftforge.fml.common.Mod;


@Mod(
        modid = RCPatch.MOD_ID,
        name = RCPatch.MOD_NAME,
        version = RCPatch.VERSION
)
public class RCPatch {

    public static final String MOD_ID = "rcpatch";
    public static final String MOD_NAME = "RCPatch";
    public static final String VERSION = "1.0-SNAPSHOT";

    @Mod.Instance(MOD_ID)
    public static RCPatch INSTANCE;
}
