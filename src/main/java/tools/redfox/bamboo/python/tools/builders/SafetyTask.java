package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.redfox.bamboo.python.tools.model.SafetyTaskProperties;

public class SafetyTask extends BaseTask<SafetyTask, SafetyTaskProperties> {
    @Nullable
    protected String input;

    public SafetyTask input(@Nullable String input) {
        this.input = input;
        configuration.put("input", input);
        return this;
    }

    @NotNull
    @Override
    protected SafetyTaskProperties build() {
        return new SafetyTaskProperties(
                description,
                taskEnabled,
                runtime,
                options,
                input,
                output,
                workingSubDirectory,
                environmentVariables,
                requirements,
                conditions
        );
    }
}
