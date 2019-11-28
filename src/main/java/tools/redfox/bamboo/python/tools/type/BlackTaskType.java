package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.junit.TestCase;

import java.util.LinkedList;
import java.util.List;

public class BlackTaskType extends BaseTaskType implements TaskType {
    public static final String NAME = "black";
    public static final String TASK_ID = "tools.redfox.bamboo.python-tools:tools.redfox.python.tools.black.task";

    public BlackTaskType(
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
        return super.execute(taskContext, "");
    }
}
