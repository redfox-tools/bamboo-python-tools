package tools.redfox.bamboo.python.tools.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.builders.SafetyTask;
import tools.redfox.bamboo.python.tools.model.SafetyTaskProperties;

import java.util.Map;

public class SafetyExporter extends BaseExporter<SafetyTaskProperties, SafetyTask> {
    protected SafetyExporter(UIConfigSupport uiConfigSupport) {
        super(SafetyTaskProperties.class, uiConfigSupport);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return SafetyTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected SafetyTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new SafetyTask();
    }
}
