package org.example;

public class Managers {

    public static TaskManager getDefault() {
        return FileBackedTasksManager.readObject("src/save");
    }
}
