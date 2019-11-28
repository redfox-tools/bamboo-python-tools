package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.model.BlackTaskProperties;

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
