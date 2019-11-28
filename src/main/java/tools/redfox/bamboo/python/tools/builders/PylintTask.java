package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.redfox.bamboo.python.tools.model.PylintTaskProperties;
import tools.redfox.bamboo.python.tools.model.SafetyTaskProperties;

public class PylintTask extends BaseTask<PylintTask, PylintTaskProperties> {
    @Nullable
    protected String input;

    public PylintTask input(@Nullable String input) {
        this.input = input;
        configuration.put("input", input);
        return this;
    }

    @NotNull
    @Override
    protected PylintTaskProperties build() {
        return new PylintTaskProperties(
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
