package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.google.common.base.Strings;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.junit.TestCase;

import java.util.LinkedList;
import java.util.List;

public class PyTestTaskType extends BaseTaskType implements TaskType {
    public static final String NAME = "pytest";
    public static final String TASK_ID = "tools.redfox.bamboo.python-tools:tools.redfox.python.tools.pytest.task";

    public PyTestTaskType(
            @ComponentImport ProcessService processService,
            @ComponentImport EnvironmentVariableAccessor environmentVariableAccessor,
            @ComponentImport CapabilityContext capabilityContext) {
        super(processService, environmentVariableAccessor, capabilityContext);
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected List<TestCase> parseOutput(String output) {
        return new LinkedList<TestCase>();
    }

    @NotNull
    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        ConfigurationMap config = taskContext.getConfigurationMap();
        String options = "";
        if (!Strings.isNullOrEmpty(config.get("output"))) {
            options += "--junit-xml=" + config.get("output");
        }
        return super.execute(taskContext, options);
    }

    @Override
    protected int getExpectedExitCode() {
        return 1;
    }
}
