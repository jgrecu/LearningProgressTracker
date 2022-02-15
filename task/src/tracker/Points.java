package tracker;

public class Points {
    private int java = 0;
    private int dsa = 0;
    private int databases = 0;
    private int spring = 0;

    public Points() {}

    public Points(int java, int dsa, int databases, int spring) {
        this.java = java;
        this.dsa = dsa;
        this.databases = databases;
        this.spring = spring;
    }

    public int getJava() {
        return java;
    }

    public void addJava(int java) {
        this.java += java;
    }

    public int getDsa() {
        return dsa;
    }

    public void addDsa(int dsa) {
        this.dsa += dsa;
    }

    public int getDatabases() {
        return databases;
    }

    public void addDatabases(int databases) {
        this.databases += databases;
    }

    public int getSpring() {
        return spring;
    }

    public void addSpring(int spring) {
        this.spring += spring;
    }

    @Override
    public String toString() {
        return "Java=" + java + "; DSA=" + dsa + "; Databases=" + databases + "; Spring=" + spring;
    }
}
