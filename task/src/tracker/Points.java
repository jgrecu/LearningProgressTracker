package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Points {
    private int java = 0;
    private int dsa = 0;
    private int databases = 0;
    private int spring = 0;
    protected Map<String, String> notifications = new LinkedHashMap<>();

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
        if (this.java >= 600 && !notifications.containsKey("Java")) {
            notifications.put("Java", "ready");
        }
    }

    public int getDsa() {
        return dsa;
    }

    public void addDsa(int dsa) {
        this.dsa += dsa;
        if (this.dsa >= 600 && !notifications.containsKey("DSA")) {
            notifications.put("DSA", "ready");
        }
    }

    public int getDatabases() {
        return databases;
    }

    public void addDatabases(int databases) {
        this.databases += databases;
        if (this.databases >= 600 && !notifications.containsKey("Databases")) {
            notifications.put("Databases", "ready");
        }
    }

    public int getSpring() {
        return spring;
    }

    public void addSpring(int spring) {
        this.spring += spring;
        if (this.spring >= 600 && !notifications.containsKey("Spring")) {
            notifications.put("Spring", "ready");
        }
    }

    public String javaStats() {
        return new BigDecimal((double) this.java * 100.0 / 600.0)
                .setScale(1, RoundingMode.HALF_UP)
                .toString();
    }

    public String dsaStats() {
        return new BigDecimal((double) this.dsa * 100.0 / 400.0)
                .setScale(1, RoundingMode.HALF_UP)
                .toString();
    }

    public String dbStats() {
        return new BigDecimal((double) this.databases * 100.0 / 480.0)
                .setScale(1, RoundingMode.HALF_UP)
                .toString();
    }

    public String springStats() {
        return new BigDecimal((double) this.spring * 100.0 / 550.0)
                .setScale(1, RoundingMode.HALF_UP)
                .toString();
    }

    public Double getCompletion(String course) {
        switch (course) {
            case "java":
                return new BigDecimal((double) this.java * 100.0 / 600.0)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
            case "dsa":
                return new BigDecimal((double) this.dsa * 100.0 / 400.0)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
            case "databases":
                return new BigDecimal((double) this.databases * 100.0 / 480.0)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
            case "spring":
                return new BigDecimal((double) this.spring * 100.0 / 550.0)
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
        }
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Points)) return false;
        Points points = (Points) o;
        return java == points.java && dsa == points.dsa && databases == points.databases && spring == points.spring;
    }

    @Override
    public int hashCode() {
        return Objects.hash(java, dsa, databases, spring);
    }

    @Override
    public String toString() {
        return "Java=" + java + "; DSA=" + dsa + "; Databases=" + databases + "; Spring=" + spring;
    }
}
