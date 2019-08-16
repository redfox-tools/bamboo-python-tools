package tools.redfox.bamboo.python.tools.builders;

import com.atlassian.bamboo.specs.api.builders.task.Task;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.redfox.bamboo.python.tools.model.PyTestTaskProperties;

public class SafetyTask extends Task<SafetyTask, PyTestTaskProperties> {
    @Nullable
    private String options;

    public SafetyTask options(@Nullable String options) {
        this.options = options;
        return this;
    }

    @NotNull
    @Override
    protected PyTestTaskProperties build() {
        return new PyTestTaskProperties(
                description,
                taskEnabled,
                options,
                requirements,
                conditions
        );
    }
}
