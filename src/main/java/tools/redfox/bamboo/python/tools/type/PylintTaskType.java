package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import tools.redfox.bamboo.base.type.BaseTaskType;
import tools.redfox.bamboo.base.type.JUnitGenratorInterface;
import tools.redfox.bamboo.python.tools.output.PylintOutputProcessor;

public class PylintTaskType extends BaseTaskType implements TaskType, JUnitGenratorInterface {
    public static final String NAME = "pylint";
    public static final String TASK_ID = "tools.redfox.bamboo.python-tools:tools.redfox.python.tools.pylint.task";

    public PylintTaskType(
            @ComponentImport ProcessService processService,
            @ComponentImport EnvironmentVariableAccessor environmentVariableAccessor,
            @ComponentImport CapabilityContext capabilityContext) {
        super(processService, environmentVariableAccessor, capabilityContext);
        processors.add(new PylintOutputProcessor());
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected String getBaseCommand() {
        return "-f json -r y --exit-zero";
    }
}
