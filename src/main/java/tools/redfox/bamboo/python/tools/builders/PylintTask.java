package tools.redfox.bamboo.python.tools.builders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.atlassian.bamboo.specs.model.tools.python.PylintTaskProperties;
import tools.redfox.bamboo.base.builder.BaseTask;

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
