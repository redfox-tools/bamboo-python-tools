package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ExternalProcessBuilder;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskType;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.base.tools.Command;
import tools.redfox.bamboo.base.type.BaseTaskType;
import tools.redfox.bamboo.base.type.JUnitGenratorInterface;
import tools.redfox.bamboo.python.tools.output.SafetyOutputProcessor;
import tools.redfox.bamboo.python.tools.provider.requirements.PipfileProvider;
import tools.redfox.bamboo.python.tools.provider.requirements.Requirement;
import tools.redfox.bamboo.python.tools.provider.requirements.RequirementProviderInterface;
import tools.redfox.bamboo.python.tools.provider.requirements.RequirementsProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SafetyTaskType extends BaseTaskType implements TaskType, JUnitGenratorInterface {
    public static final String NAME = "safety";
    public static final String TASK_ID = "tools.redfox.bamboo.python-tools:tools.redfox.python.tools.safety.task";
    private List<Requirement> requirements = new LinkedList<>();

    public SafetyTaskType(
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
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        requirements = getInstalledRequirements(taskContext);
        processors.add(new SafetyOutputProcessor(requirements));
        return super.execute(taskContext);
    }

    @Override
    protected ExternalProcessBuilder buildProcess(Command command, TaskContext taskContext) {
        ExternalProcessBuilder process = super.buildProcess(command, taskContext);
        process.input(
                requirements.stream().map(r -> r.toString() + "\n").reduce("", String::concat)
        );

        return process;
    }

    @Override
    protected String getBaseCommand() {
        return " check --stdin --json ";
    }

    private List<Requirement> getInstalledRequirements(TaskContext taskContext) {
        HashMap<String, RequirementProviderInterface> parsers = new HashMap<String, RequirementProviderInterface>() {{
            put("Pipfile.lock", new PipfileProvider());
            put("requirements.txt", new RequirementsProvider());
            put("requirements-dev.txt", new RequirementsProvider());
            put("poetry.lock", new RequirementsProvider());
        }};
        ;

        RequirementProviderInterface provider = parsers.get(taskContext.getConfigurationMap().get("input"));

        try {
            String inputFile = taskContext.getWorkingDirectory().getAbsoluteFile() + "/" + taskContext.getConfigurationMap().get("input");
            return provider.getRequirements(new File(inputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
