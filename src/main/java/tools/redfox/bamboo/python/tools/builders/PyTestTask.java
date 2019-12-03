package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import com.atlassian.bamboo.specs.model.tools.python.PyTestTaskProperties;
import tools.redfox.bamboo.base.builder.BaseTask;

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
