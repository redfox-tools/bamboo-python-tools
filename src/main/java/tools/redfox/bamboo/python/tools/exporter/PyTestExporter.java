package tools.redfox.bamboo.python.tools.exporter;

import com.atlassian.bamboo.specs.api.validators.common.ValidationContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.UIConfigSupport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.exporter.BaseExporter;
import tools.redfox.bamboo.python.tools.builders.PyTestTask;
import com.atlassian.bamboo.specs.model.tools.python.PyTestTaskProperties;

import java.util.Map;

public class PyTestExporter extends BaseExporter<PyTestTaskProperties, PyTestTask> {
    protected PyTestExporter() {
        super(PyTestTaskProperties.class);
    }

    @NotNull
    @Override
    protected ValidationContext getValidationContext() {
        return PyTestTaskProperties.VALIDATION_CONTEXT;
    }

    @NotNull
    @Override
    protected PyTestTask toSpecsEntity(@NotNull Map<String, String> configuration) {
        return new PyTestTask();
    }
}
