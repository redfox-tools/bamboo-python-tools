package tools.redfox.bamboo.python.tools.model;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.builders.PyTestTask;

import java.util.List;

@Builder(PyTestTask.class)
public class PyTestTaskProperties extends BaseTaskProperties {
    public PyTestTaskProperties() {
    }

    public PyTestTaskProperties(String description, boolean enabled, String options, @NotNull List<RequirementProperties> requirements, @NotNull List<? extends ConditionProperties> conditions) throws PropertiesValidationException {
        super(description, enabled, requirements, conditions);
        this.options = options;
    }

    @Override
    protected String getModulePropertiesName() {
        return "tools.redfox.bamboo.python.tools.pytest:pytest";
    }
}
