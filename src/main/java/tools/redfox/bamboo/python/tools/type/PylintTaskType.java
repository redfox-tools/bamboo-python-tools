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
import org.json.JSONArray;
import org.json.JSONObject;
import tools.redfox.bamboo.python.tools.junit.Failure;
import tools.redfox.bamboo.python.tools.junit.Success;
import tools.redfox.bamboo.python.tools.junit.TestCase;
import tools.redfox.bamboo.python.tools.provider.requirements.Requirement;
import tools.redfox.bamboo.python.tools.type.BaseTaskType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PylintTaskType extends BaseTaskType implements TaskType {
    public static final String NAME = "pylint";
    public static final String TASK_ID = "tools.redfox.bamboo.python-tools:tools.redfox.python.tools.pylint.task";

    public PylintTaskType(
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
        JSONArray json = new JSONArray(output);

        for (int i = 0; i < json.length(); i++) {
            JSONObject e = json.getJSONObject(i);
            cases.add(new TestCase(
                    String.format("%s: %s:%s (%d:%d)", e.getString("message-id"), e.getString("module"),
                            e.getString("obj"), e.getInt("line"), e.getInt("column")),
                    new Failure(String.format("%s file %s line %s column: %s\n %s", e.getString("symbol"),
                            e.getString("path"), e.getInt("line"), e.getInt("column"),
                            e.getString("message").replace("\\n", "\n")),
                            e.getString("message-id"))
            ));
        }

        return cases;
    }

    @NotNull
    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        return super.execute(taskContext, "-f json -r y --exit-zero");
    }
}
