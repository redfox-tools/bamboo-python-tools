package tools.redfox.bamboo.python.tools.provider.requirements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

abstract public class AbstractRequirementsProvider implements RequirementProviderInterface {
    protected String readFile(File source) throws IOException {
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);
        String line;
        StringBuffer content = new StringBuffer();
        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }
}
