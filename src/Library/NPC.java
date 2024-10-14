package Library;

public abstract class NPC {
    protected String name;

    public NPC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void speak();
}
