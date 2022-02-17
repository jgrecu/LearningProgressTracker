package tracker;

public enum Commands {
    EXIT("exit"), ADD_STUDENTS("add students"), BACK("back"), LIST("list"),
    ADD_POINTS("add points"), FIND("find"), STATISTICS("statistics"), NOTIFY("notify");

    public final String value;

    Commands(String value) {
        this.value = value;
    }
}
