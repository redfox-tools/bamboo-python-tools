package tools.redfox.bamboo.python.tools.output;

import org.json.JSONArray;
import org.json.JSONObject;
import tools.redfox.bamboo.base.tools.junit.Failure;
import tools.redfox.bamboo.base.tools.junit.TestCase;
import tools.redfox.bamboo.base.tools.output.handler.BaseJUnitProcessor;

import java.util.LinkedList;
import java.util.List;

public class PylintOutputProcessor extends BaseJUnitProcessor {
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
}
