package tools.redfox.bamboo.python.tools.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.exporter.BaseExporter;
import tools.redfox.bamboo.python.tools.builders.SafetyTask;
import com.atlassian.bamboo.specs.model.tools.python.SafetyTaskProperties;

import java.util.Map;

public class SafetyExporter extends BaseExporter<SafetyTaskProperties, SafetyTask> {
    protected SafetyExporter() {
        super(SafetyTaskProperties.class);
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
