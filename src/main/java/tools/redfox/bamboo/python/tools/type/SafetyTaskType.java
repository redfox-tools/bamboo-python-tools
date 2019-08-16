package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import org.jetbrains.annotations.NotNull;

public class SafetyTaskType extends BaseTaskType implements TaskType {
    public SafetyTaskType(ProcessService processService, CapabilityContext capabilityContext) {
        super(processService, capabilityContext);
    }

    @Override
    protected String getName() {
        return "safety";
    }

    @NotNull
    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        return super.execute(taskContext);
    }
}
