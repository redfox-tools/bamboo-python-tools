package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.ExternalProcessBuilder;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.utils.process.ExternalProcess;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

abstract class BaseTaskType {
    private ProcessService processService;
    private CapabilityContext capabilityContext;

    protected BaseTaskType(
            @ComponentImport final ProcessService processService,
            @ComponentImport CapabilityContext capabilityContext
    ) {
        this.processService = processService;
        this.capabilityContext = capabilityContext;
    }

    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        final TaskResultBuilder builder = TaskResultBuilder.newBuilder(taskContext);
        final String[] options = taskContext.getConfigurationMap().get("tools.redfox.python.tools." + this.getName() + ".options").split("\\s+");
        List<String> optionsList = new LinkedList<>(Arrays.asList(options));
        optionsList.removeIf(String::isEmpty);

        List<String> args = new LinkedList<>();
        args.add(capabilityContext.getCapabilityValue("tools.redfox.python.tools." + this.getName() + ".executable")); // /Users/danielancuta/Library/Caches/pypoetry/virtualenvs/merlin-lCUD6KrQ-py3.6/bin/pytest
        if (!optionsList.isEmpty()) {
            args.addAll(optionsList);
        }
        args.add(".");

        ExternalProcess process = processService.createExternalProcess(taskContext,
                new ExternalProcessBuilder()
                        .command(args)
                        .workingDirectory(taskContext.getWorkingDirectory()));

        taskContext.getBuildLogger().addBuildLogEntry("Executing " + this.getName() + " with: " + process.getCommandLine());
        process.execute();

        builder.checkReturnCode(process, 0);

        return builder.build();
    }

    abstract protected String getName();
}
