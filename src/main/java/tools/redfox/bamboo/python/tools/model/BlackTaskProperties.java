package tools.redfox.bamboo.python.tools.model;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.builders.BlackTask;
import tools.redfox.bamboo.python.tools.type.BlackTaskType;

import java.util.List;

@Builder(BlackTask.class)
public class BlackTaskProperties extends BaseTaskProperties {
    public static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of(BlackTaskType.NAME);
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties(BlackTaskType.TASK_ID);

    public BlackTaskProperties() {
        super();
    }

    public BlackTaskProperties(
            String description,
            boolean enabled,
            String runtime,
            String options,
            String output,
            String workingSubDirectory,
            String environmentVariables,
            @NotNull List<RequirementProperties> requirements,
            @NotNull List<? extends ConditionProperties> conditions) throws PropertiesValidationException {
        super(description, enabled, runtime, options, output, workingSubDirectory, environmentVariables, requirements, conditions);
    }

    @Override
    protected String getModulePropertiesName() {
        return BlackTaskType.TASK_ID;
    }
}
