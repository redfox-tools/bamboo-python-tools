package tools.redfox.bamboo.python.tools.builders;

import com.atlassian.bamboo.specs.api.builders.task.Task;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.redfox.bamboo.python.tools.model.BlackTaskProperties;

public class BlackTask extends Task<BlackTask, BlackTaskProperties> {
    @Nullable
    private String options;

    public BlackTask options(@Nullable String options) {
        this.options = options;
        return this;
    }

    @NotNull
    @Override
    protected BlackTaskProperties build() {
        return new BlackTaskProperties(
                description,
                taskEnabled,
                options,
                requirements,
                conditions
        );
    }
}
