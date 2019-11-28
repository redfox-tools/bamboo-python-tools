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
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import tools.redfox.bamboo.python.tools.junit.Failure;
import tools.redfox.bamboo.python.tools.junit.Success;
import tools.redfox.bamboo.python.tools.junit.TestCase;
import tools.redfox.bamboo.python.tools.provider.requirements.PipfileProvider;
import tools.redfox.bamboo.python.tools.provider.requirements.Requirement;
import tools.redfox.bamboo.python.tools.provider.requirements.RequirementProviderInterface;
import tools.redfox.bamboo.python.tools.provider.requirements.RequirementsProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SafetyTaskType extends BaseTaskType implements TaskType {
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
    protected List<TestCase> parseOutput(String output) {
        List<TestCase> cases = new LinkedList<>();
        HashMap<String, JSONArray> vulnerable = new HashMap<>();
        JSONArray json = new JSONArray(output);

        for (int i = 0; i < json.length(); i++) {
            JSONArray e = json.getJSONArray(i);
            vulnerable.put(e.getString(0), e);
        }

        for (Requirement r : requirements) {
            JSONArray v;
            if (vulnerable.containsKey(r.getName())) {
                v = vulnerable.get(r.getName());
                cases.add(new TestCase(
                        r.toString(),
                        new Failure(String.format("%s (%s). %s", v.getString(0), v.getString(1), v.getString(3)), v.getString(4))
                ));
            } else {
                cases.add(new TestCase(r.toString(), new Success("OK", "safe")));
            }
        }

        return cases;
    }

    @NotNull
    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        return super.execute(taskContext, " check --stdin --json ");
    }

    @Override
    public ExternalProcessBuilder buildProcess(ImmutableList.Builder commandListBuilder, TaskContext taskContext, Map<String, String> extraEnvironmentVariables, String executablePath) {
        ExternalProcessBuilder process = super.buildProcess(commandListBuilder, taskContext, extraEnvironmentVariables, executablePath);

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
            requirements = provider.getRequirements(new File(inputFile));

            String input = requirements.stream().map(r -> r.toString() + "\n").reduce("", String::concat);
            process.input(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return process;
    }
}
