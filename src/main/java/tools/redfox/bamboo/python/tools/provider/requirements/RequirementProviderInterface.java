package tools.redfox.bamboo.python.tools.provider.requirements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface RequirementProviderInterface {
    public List<Requirement> getRequirements(File source) throws IOException;
}
