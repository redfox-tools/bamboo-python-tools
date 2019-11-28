package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.model.BlackTaskProperties;
import tools.redfox.bamboo.python.tools.model.PyTestTaskProperties;

public class PyTestTask extends BaseTask<PyTestTask, PyTestTaskProperties> {
    @NotNull
    @Override
    protected PyTestTaskProperties build() {
        return new PyTestTaskProperties(
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
