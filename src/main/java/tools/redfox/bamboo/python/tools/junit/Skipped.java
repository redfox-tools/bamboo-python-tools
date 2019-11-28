package tools.redfox.bamboo.python.tools.junit;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "skipped")
public class Skipped extends BaseResult {
    public Skipped(String message, String type) {
        super(message, type);
    }
}
