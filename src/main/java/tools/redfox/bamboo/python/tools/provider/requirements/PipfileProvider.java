package tools.redfox.bamboo.python.tools.provider.requirements;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PipfileProvider extends AbstractRequirementsProvider implements RequirementProviderInterface {
    public PipfileProvider() {

    }

    public List<Requirement> getRequirements(File source) throws IOException {
        JSONObject json = new JSONObject(readFile(source));
        List<Requirement> requirements = new ArrayList<>();

        JSONObject dependencies = json.getJSONObject("default");
        for (Iterator<String> it = dependencies.keys(); it.hasNext(); ) {
            String name = it.next();
            String version = dependencies.getJSONObject(name).getString("version");
            requirements.add(new Requirement(name, version));
        }

        JSONObject devDependencies = json.getJSONObject("develop");
        for (Iterator<String> it = devDependencies.keys(); it.hasNext(); ) {
            String name = it.next();
            String version = devDependencies.getJSONObject(name).getString("version").replace("==", "");
            requirements.add(new Requirement(name, version, true));
        }

        return requirements;
    }
}
