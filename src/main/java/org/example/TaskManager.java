package org.example;

import java.util.ArrayList;
import java.util.UUID;

public interface TaskManager {

    <T> ArrayList<T> getAllTasks();

    void deleteAllTasks();

    Task getTaskById(UUID uuid);

    Epic getEpicById(UUID epicId);

    Task getSubTaskById(UUID subTaskId, UUID epicId);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(UUID uuid, SubTask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void deleteTaskById(UUID uuid);

    void deleteEpicById(UUID uuid);

    ArrayList<Task> getAllSubtasksOfEpic(UUID uuid);
}
