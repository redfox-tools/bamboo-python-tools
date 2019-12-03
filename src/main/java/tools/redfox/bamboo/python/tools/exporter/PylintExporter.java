package tools.redfox.bamboo.python.tools.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.exporter.BaseExporter;
import tools.redfox.bamboo.python.tools.builders.PylintTask;
import com.atlassian.bamboo.specs.model.tools.python.PylintTaskProperties;

import java.util.Map;

public class PylintExporter extends BaseExporter<PylintTaskProperties, PylintTask> {
    protected PylintExporter() {
        super(PylintTaskProperties.class);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return PylintTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected PylintTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new PylintTask();
    }
}
