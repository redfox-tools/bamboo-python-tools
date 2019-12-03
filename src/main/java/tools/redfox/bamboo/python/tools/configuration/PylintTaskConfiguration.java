package tools.redfox.bamboo.python.tools.configuration;

import com.atlassian.bamboo.task.TaskConfiguratorHelper;
import com.atlassian.bamboo.task.TaskRequirementSupport;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.configuration.BaseTaskConfiguration;
import tools.redfox.bamboo.python.tools.type.PylintTaskType;

public class PylintTaskConfiguration extends BaseTaskConfiguration implements TaskRequirementSupport {
    public PylintTaskConfiguration(@ComponentImport UIConfigSupport uiConfigSupport, @ComponentImport TaskConfiguratorHelper taskConfiguratorHelper) {
        super(uiConfigSupport, taskConfiguratorHelper);
    }

    @NotNull
    @Override
    protected String getExecutableName() {
        return PylintTaskType.NAME;
    }
}
