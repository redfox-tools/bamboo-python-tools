package tools.redfox.bamboo.python.tools.configuration;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.task.TaskRequirementSupport;
import com.atlassian.bamboo.v2.build.agent.capability.Requirement;
import com.atlassian.bamboo.v2.build.agent.capability.RequirementImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class BlackTaskConfiguration extends AbstractTaskConfigurator implements TaskRequirementSupport {
    @Override
    @NotNull
    public Set<Requirement> calculateRequirements(@NotNull TaskDefinition taskDefinition) {
        return new LinkedHashSet<Requirement>() {{
            add(new RequirementImpl("tools.redfox.python.tools.black.executable", true, ".*"));
        }};
    }

    @Override
    @NotNull
    public Map<String, String> generateTaskConfigMap(@NotNull final ActionParametersMap params, @Nullable final TaskDefinition previousTaskDefinition) {
        final Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);

        config.put("tools.redfox.python.tools.black.options", params.getString("tools.redfox.python.tools.black.options"));

        return config;
    }

    @Override
    public void populateContextForCreate(@NotNull final Map<String, Object> context)
    {
        super.populateContextForCreate(context);

        context.put("tools.redfox.python.tools.black.options", "--check");
    }

    @Override
    public void populateContextForEdit(@NotNull final Map<String, Object> context, @NotNull final TaskDefinition taskDefinition)
    {
        super.populateContextForEdit(context, taskDefinition);

        context.put("tools.redfox.python.tools.black.options", taskDefinition.getConfiguration().get("tools.redfox.python.tools.black.options"));
    }
}
