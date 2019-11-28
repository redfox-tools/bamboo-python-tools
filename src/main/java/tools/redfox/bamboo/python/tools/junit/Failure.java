package tools.redfox.bamboo.python.tools.junit;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "failure")
public class Failure extends BaseResult {
    public Failure(String message, String type) {
        super(message, type);
    }
}
