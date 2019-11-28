package tools.redfox.bamboo.python.tools.provider.requirements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PoetryProvider extends AbstractRequirementsProvider implements RequirementProviderInterface {
    public PoetryProvider() {

    }

    public List<Requirement> getRequirements(File source) throws IOException {
        return new LinkedList<>();
    }
}
