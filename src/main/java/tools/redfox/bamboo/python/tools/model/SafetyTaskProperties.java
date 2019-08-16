package tools.redfox.bamboo.python.tools.model;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.builders.PyTestTask;

import java.util.List;

@Builder(PyTestTask.class)
public class SafetyTaskProperties extends TaskProperties {
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties("tools.redfox.bamboo.python.tools.pytest:pytest");
    private String options;

    public SafetyTaskProperties() {
    }

    public SafetyTaskProperties(String description, boolean enabled, String options, @NotNull List<RequirementProperties> requirements, @NotNull List<? extends ConditionProperties> conditions) throws PropertiesValidationException {
        super(description, enabled, requirements, conditions);
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    @NotNull
    @Override
    public AtlassianModuleProperties getAtlassianPlugin() {
        return ATLASSIAN_PLUGIN;
    }
}
