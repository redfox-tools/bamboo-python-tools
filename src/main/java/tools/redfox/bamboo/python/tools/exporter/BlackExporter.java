package tools.redfox.bamboo.python.tools.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.builders.BlackTask;
import tools.redfox.bamboo.python.tools.model.BlackTaskProperties;

import java.util.Map;

public class BlackExporter extends BaseExporter<BlackTaskProperties, BlackTask> {
    protected BlackExporter(UIConfigSupport uiConfigSupport) {
        super(BlackTaskProperties.class, uiConfigSupport);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return BlackTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected BlackTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new BlackTask();
    }
}
