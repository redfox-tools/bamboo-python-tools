package com.atlassian.bamboo.specs.model.tools.python;

import com.atlassian.bamboo.specs.api.codegen.annotations.Builder;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.model.AtlassianModuleProperties;
import com.atlassian.bamboo.specs.api.model.plan.condition.ConditionProperties;
import com.atlassian.bamboo.specs.api.model.plan.requirement.RequirementProperties;
import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.model.BaseTaskProperties;
import tools.redfox.bamboo.python.tools.builders.SafetyTask;
import tools.redfox.bamboo.python.tools.type.SafetyTaskType;

import java.util.List;

@Builder(SafetyTask.class)
public class SafetyTaskProperties extends BaseTaskProperties {
    public static final ValidationContext VALIDATION_CONTEXT = ValidationContext.of(SafetyTaskType.NAME);
    private static final AtlassianModuleProperties ATLASSIAN_PLUGIN =
            new AtlassianModuleProperties(SafetyTaskType.TASK_ID);

    private String input;

    public SafetyTaskProperties() {
        super();
    }

    public SafetyTaskProperties(
            String description,
            boolean enabled,
            String runtime,
            String options,
            String input,
            String output,
            String workingSubDirectory,
            String environmentVariables,
            @NotNull List<RequirementProperties> requirements,
            @NotNull List<? extends ConditionProperties> conditions) throws PropertiesValidationException {
        super(description, enabled, runtime, options, output, workingSubDirectory, environmentVariables, requirements, conditions);
        this.input = input;
    }

    @Override
    protected String getModulePropertiesName() {
        return SafetyTaskType.TASK_ID;
    }

    public String getInput() {
        return input;
    }
}
