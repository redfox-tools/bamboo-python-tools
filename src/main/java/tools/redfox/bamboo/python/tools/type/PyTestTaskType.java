package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import org.jetbrains.annotations.NotNull;

public class PyTestTaskType extends BaseTaskType implements TaskType {
    public PyTestTaskType(ProcessService processService, CapabilityContext capabilityContext) {
        super(processService, capabilityContext);
    }

    @Override
    protected String getName() {
        return "pytest";
    }

    @NotNull
    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        return super.execute(taskContext);
    }
}
