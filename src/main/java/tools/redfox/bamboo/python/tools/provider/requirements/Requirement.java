package tools.redfox.bamboo.python.tools.provider.requirements;

public class Requirement {
    private String name;
    private String version;
    private boolean dev = false;

    public Requirement(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public Requirement(String name, String version, boolean dev) {
        this(name, version);
        this.dev = dev;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public boolean isDev() {
        return dev;
    }

    @Override
    public String toString() {
        return String.format("%s%s", name, version);
    }
}
