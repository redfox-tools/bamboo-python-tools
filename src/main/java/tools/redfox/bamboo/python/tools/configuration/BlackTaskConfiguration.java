package tools.redfox.bamboo.python.tools.configuration;

import com.atlassian.bamboo.task.TaskConfiguratorHelper;
import com.atlassian.bamboo.task.TaskRequirementSupport;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.configuration.BaseTaskConfiguration;
import tools.redfox.bamboo.python.tools.type.BlackTaskType;

import java.util.Set;

public class BlackTaskConfiguration extends BaseTaskConfiguration implements TaskRequirementSupport {
    public BlackTaskConfiguration(@ComponentImport UIConfigSupport uiConfigSupport, @ComponentImport TaskConfiguratorHelper taskConfiguratorHelper) {
        super(uiConfigSupport, taskConfiguratorHelper);
    }

    @NotNull
    @Override
    protected Set<String> getFieldsToCopy() {
        Set<String> fields = super.getFieldsToCopy();
        fields.remove("output");
        return fields;
    }

    @NotNull
    @Override
    protected String getExecutableName() {
        return BlackTaskType.NAME;
    }
}
