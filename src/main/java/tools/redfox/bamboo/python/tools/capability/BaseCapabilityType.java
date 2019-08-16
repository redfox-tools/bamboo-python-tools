package tools.redfox.bamboo.python.tools.capability;

import com.atlassian.bamboo.v2.build.agent.capability.AbstractCapabilityTypeModule;
import com.atlassian.bamboo.v2.build.agent.capability.Capability;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

abstract class BaseCapabilityType extends AbstractCapabilityTypeModule {
    @NotNull
    @Override
    public Map<String, String> validate(@NotNull Map<String, String[]> map) {
        return Collections.emptyMap();
    }

    @NotNull
    @Override
    public Capability getCapability(@NotNull Map<String, String[]> map) {
        return new CapabilityImpl(
                "tools.redfox.python.tools.black.executable",
                Arrays.stream(map.get("tools.redfox.python.tools.black.executable")).filter(c-> !c.isEmpty()).findFirst().orElse("")
        );
    }

    abstract protected String getLabel();
}
