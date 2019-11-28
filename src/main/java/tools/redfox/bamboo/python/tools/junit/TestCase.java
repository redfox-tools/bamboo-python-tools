package tools.redfox.bamboo.python.tools.junit;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "testcase")
public class TestCase {
    private String name;
    private Failure failure;
    private Skipped skipped;
    private Success success;

    public TestCase() {
    }

    public TestCase(String name, Failure failure) {
        this.name = name;
        this.failure = failure;
    }

    public TestCase(String name, Skipped skipped) {
        this.name = name;
        this.skipped = skipped;
    }

    public TestCase(String name, Success success) {
        this.name = name;
        this.success = success;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    @XmlElement
    public Failure getFailure() {
        return failure;
    }

    @XmlElement
    public Skipped getSkipped() {
        return skipped;
    }

    @XmlElement
    public Success getSuccess() {
        return success;
    }
}
