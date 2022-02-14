package tracker;

public enum Commands {
    EXIT("exit"), ADD_USER("add user"), BACK("back");

    public final String command;

    Commands(String command) {
        this.command = command;
    }
}
