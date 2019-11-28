package tools.redfox.bamboo.python.tools.type;

import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.process.CommandlineStringUtils;
import com.atlassian.bamboo.process.EnvironmentVariableAccessor;
import com.atlassian.bamboo.process.ExternalProcessBuilder;
import com.atlassian.bamboo.process.ProcessService;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.v2.build.agent.capability.CapabilityContext;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import tools.redfox.bamboo.python.tools.junit.Success;
import tools.redfox.bamboo.python.tools.junit.TestCase;
import tools.redfox.bamboo.python.tools.junit.TestSuite;
import tools.redfox.bamboo.python.tools.wrappers.WrappedBuildLogger;
import tools.redfox.bamboo.python.tools.wrappers.WrappedTaskContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

abstract class BaseTaskType {
    private final EnvironmentVariableAccessor environmentVariableAccessor;
    private ProcessService processService;
    private CapabilityContext capabilityContext;

    protected BaseTaskType(
            ProcessService processService,
            EnvironmentVariableAccessor environmentVariableAccessor,
            CapabilityContext capabilityContext) {
        this.processService = processService;
        this.environmentVariableAccessor = environmentVariableAccessor;
        this.capabilityContext = capabilityContext;
    }

    public TaskResult execute(@NotNull TaskContext taskContext, String extraOptions) throws TaskException {
        try {
            WrappedTaskContext wrappedTaskContext = new WrappedTaskContext(taskContext);
            ConfigurationMap configurationMap = taskContext.getConfigurationMap();

            String executablePath = this.capabilityContext.getCapabilityValue(String.format("system.builder.%s.%s", getName(), configurationMap.get("runtime")));
            Map<String, String> extraEnvironmentVariables = this.environmentVariableAccessor.splitEnvironmentAssignments(configurationMap.get("environmentVariables"), false);

            assert executablePath != null;

            List<String> arguments = CommandlineStringUtils.tokeniseCommandline(extraOptions + " " + configurationMap.get("options"));

            ImmutableList.Builder<String> commandListBuilder = ImmutableList.builder();
            commandListBuilder
                    .add(executablePath)
                    .addAll(arguments);

            taskContext.getBuildLogger().addBuildLogEntry("Exec: " + String.join(" ", commandListBuilder.build()));

            TaskResultBuilder taskResultBuilder = TaskResultBuilder.newBuilder(taskContext);
            taskResultBuilder.checkReturnCode(
                    this.processService.executeExternalProcess(
                            wrappedTaskContext,
                            buildProcess(commandListBuilder, taskContext, extraEnvironmentVariables, executablePath)
                    )
            );

            String output = configurationMap.getOrDefault("output", null);

            if (!Strings.isNullOrEmpty(output)) {
                generateJUnit(taskContext, (WrappedBuildLogger) wrappedTaskContext.getBuildLogger(), output);
                return taskResultBuilder.success().build();
            }

            return taskResultBuilder.build();
        } catch (Exception e) {
            taskContext.getBuildLogger().addErrorLogEntry("Failed to execute task: " + e.getMessage());
            throw new TaskException("Failed to execute task", e);
        }
    }

    protected void generateJUnit(TaskContext taskContext, WrappedBuildLogger buildLogger, String output) throws TransformerException, ParserConfigurationException, JAXBException, FileNotFoundException {
        output = Paths.get(taskContext.getRootDirectory().getAbsolutePath(), output).toString();
        buildLogger.getRawBuildLogger().addBuildLogEntry("Writing check output: " + output);

        JAXBContext contextObj = JAXBContext.newInstance(TestSuite.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        TestSuite testSuite = new TestSuite("Dependency check: " + getName());
        testSuite.addTestCases(parseOutput(buildLogger.getTaskLog()));

        if (testSuite.getTestCase().size() == 0) {
            testSuite.addTestCase(
                    new TestCase(
                            String.format("All %s checks passed.", getName()),
                            new Success("All checks passed.", "success")
                    )
            );
        }

        marshallerObj.marshal(testSuite, new FileOutputStream(output));
    }

    public ExternalProcessBuilder buildProcess(ImmutableList.Builder commandListBuilder, TaskContext taskContext, Map<String, String> extraEnvironmentVariables, String executablePath) {
        return new ExternalProcessBuilder()
                .command(commandListBuilder.build())
                .env(extraEnvironmentVariables)
                .path(FilenameUtils.getFullPath(executablePath))
                .workingDirectory(taskContext.getWorkingDirectory());
    }

    abstract protected String getName();

    abstract protected List<TestCase> parseOutput(String output);
}
