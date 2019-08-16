package tools.redfox.bamboo.python.tools.model;

import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.model.task.TaskProperties;
import org.jetbrains.annotations.NotNull;

import java.util.List;

abstract class BaseTaskProperties extends TaskProperties {
    protected String options;

    public BaseTaskProperties(){}

    public BaseTaskProperties(String description, boolean enabled, List<RequirementProperties> requirements, List<? extends ConditionProperties> conditions) {
        super(description, enabled, requirements, conditions);
    }

    public String getOptions() {
        return options;
    }

    @NotNull
    @Override
    public AtlassianModuleProperties getAtlassianPlugin() {
        return new AtlassianModuleProperties(this.getModulePropertiesName());
    }

    abstract protected String getModulePropertiesName();
}
