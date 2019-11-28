package tools.redfox.bamboo.python.tools.configuration;

import com.atlassian.bamboo.task.TaskConfiguratorHelper;
import com.atlassian.bamboo.task.TaskRequirementSupport;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.type.SafetyTaskType;

import java.util.Set;

public class SafetyTaskConfiguration extends BaseTaskConfiguration implements TaskRequirementSupport {
    public SafetyTaskConfiguration(@ComponentImport UIConfigSupport uiConfigSupport, @ComponentImport TaskConfiguratorHelper taskConfiguratorHelper) {
        super(uiConfigSupport, taskConfiguratorHelper);
    }

    @NotNull
    @Override
    protected Set<String> getFieldsToCopy() {
        Set<String> fields = super.getFieldsToCopy();
        fields.add("input");
        return fields;
    }

    @NotNull
    @Override
    protected String getExecutableName() {
        return SafetyTaskType.NAME;
    }
}
