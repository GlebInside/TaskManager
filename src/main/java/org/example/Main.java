package org.example;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager();
        Task task = new Task("Task1", " Def1", UUID.randomUUID(), Status.NEW);
        manager.createTask(task);
        manager.createTask(new Task("Task2", " Def2", UUID.randomUUID(), Status.IN_PROGRESS));
        Epic epic = new Epic("Epic1", "EpicDesc1", UUID.randomUUID());
        manager.createEpic(epic);
        SubTask subTask = new SubTask("SubT1", "SubDesc1", UUID.randomUUID(), Status.NEW);
        manager.createSubtask(epic.getId(), subTask);
        SubTask subtask2 = new SubTask("SubT2", "SubDesc2", UUID.randomUUID(), Status.DONE);
        manager.createSubtask(epic.getId(), subtask2);
        Epic epic2 = new Epic("Epic2", "EpicDesc2", UUID.randomUUID());
        manager.createEpic(epic2);
        SubTask subTask3 = new SubTask("SubT3", "SubDesc3", UUID.randomUUID(), Status.IN_PROGRESS);
        manager.createSubtask(epic2.getId(), subTask3);
        subTask.setStatus(Status.DONE);
        manager.createSubtask(epic.getId(), subTask);
        manager.createEpic(epic);
        task.setStatus(Status.DONE);
        Epic epic3 = new Epic("Epic3", "EpicDesc3", UUID.randomUUID());
        manager.createEpic(epic3);
        fileBackedTasksManager.createTask(task);
        fileBackedTasksManager.createEpic(epic);
        fileBackedTasksManager.getTaskById(task.getId());
        fileBackedTasksManager.getEpicById(epic.getId());
        fileBackedTasksManager.historyManager.getHistory();
        var m = Managers.getDefault();
        System.out.println(m.getAllTasks());
    }
}