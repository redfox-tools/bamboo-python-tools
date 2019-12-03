package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import com.atlassian.bamboo.specs.model.tools.python.BlackTaskProperties;
import tools.redfox.bamboo.base.builder.BaseTask;

public class BlackTask extends BaseTask<BlackTask, BlackTaskProperties> {
    @NotNull
    @Override
    protected BlackTaskProperties build() {
        return new BlackTaskProperties(
                description,
                taskEnabled,
                runtime,
                options,
                output,
                workingSubDirectory,
                environmentVariables,
                requirements,
                conditions
        );
    }
}
