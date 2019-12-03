package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.CommandlineStringUtils;
import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import tools.redfox.bamboo.base.tools.Command;
import tools.redfox.bamboo.base.type.BaseTaskType;

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
    protected Command getCommand() {
        Command command = super.getCommand();
        if (hasOption("output")) {
            command.addAll(0, CommandlineStringUtils.tokeniseCommandline("--junit-xml=" + configurationMap.get("output")));
        }
        return command;
    }

    @Override
    protected int getExpectedExitCode() {
        return 1;
    }
}
