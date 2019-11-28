package tools.redfox.bamboo.python.tools.provider.requirements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RequirementsProvider extends AbstractRequirementsProvider {
    public RequirementsProvider() {

    }

    public List<Requirement> getRequirements(File source) throws IOException {
        return new LinkedList<>();
    }
}
