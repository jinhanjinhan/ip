package juno.launcher;
import javafx.application.Application;
import juno.Juno;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Juno.class, args);
    }
}
