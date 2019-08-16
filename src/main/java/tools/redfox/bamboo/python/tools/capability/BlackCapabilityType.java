package tools.redfox.bamboo.python.tools.capability;

import com.atlassian.bamboo.template.TemplateRenderer;
import com.atlassian.bamboo.v2.build.agent.capability.AbstractCapabilityTypeModule;
import com.atlassian.bamboo.v2.build.agent.capability.Capability;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityImpl;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.opensymphony.xwork2.TextProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class BlackCapabilityType extends AbstractCapabilityTypeModule {
    public BlackCapabilityType(@ComponentImport TemplateRenderer templateRenderer, @ComponentImport TextProvider textProvider) {
        setTemplateRenderer(templateRenderer);
    }

    @Override
    @NotNull
    public Map<String, String> validate(@NotNull Map<String, String[]> map) {
        return Collections.emptyMap();
    }

    @Override
    @NotNull
    public Capability getCapability(Map<String, String[]> map) {
        return new CapabilityImpl(
                "tools.redfox.python.tools.black.executable",
                Arrays.stream(map.get("tools.redfox.python.tools.black.executable")).filter(c-> !c.isEmpty()).findFirst().orElse("")
        );
    }

    @Override
    @NotNull
    public String getLabel(@NotNull String s) {
        return "black";
    }
}
