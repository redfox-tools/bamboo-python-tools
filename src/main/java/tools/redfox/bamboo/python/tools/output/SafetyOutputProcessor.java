package tools.redfox.bamboo.python.tools.output;

import org.json.JSONArray;
import tools.redfox.bamboo.base.tools.junit.Failure;
import tools.redfox.bamboo.base.tools.junit.Success;
import tools.redfox.bamboo.base.tools.junit.TestCase;
import tools.redfox.bamboo.base.tools.output.handler.BaseJUnitProcessor;
import tools.redfox.bamboo.python.tools.provider.requirements.Requirement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SafetyOutputProcessor extends BaseJUnitProcessor {
    private List<Requirement> requirements;

    public SafetyOutputProcessor(List<Requirement> requirements) {
        this.requirements = requirements;
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
}
