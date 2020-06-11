package maxwell_lt.rcpatch;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Collections;

public class RailcraftPatchDummyContainer extends DummyModContainer {

    public RailcraftPatchDummyContainer() {
        super(new ModMetadata());
        ModMetadata metadata = super.getMetadata();
        metadata.authorList = Collections.singletonList("maxwell_lt");
        metadata.modId = "rcpatch-core";
        metadata.version = "1.0";
        metadata.name = "RCPatchCore";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}
