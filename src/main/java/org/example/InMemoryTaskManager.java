package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
public class InMemoryTaskManager implements TaskManager {
    HashMap<UUID, Task> tasks = new HashMap<>();
    HashMap<UUID, Epic> epics = new HashMap<>();

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Override
    public <T> ArrayList<T> getAllTasks() {
        List<Task> allTasks = new ArrayList<>(tasks.values());
        allTasks.addAll(epics.values());
        return (ArrayList<T>) allTasks;
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        System.out.println("All tasks have been removed");
    }

    @Override
    public Task getTaskById(UUID taskId) {
        historyManager.add(tasks.get(taskId));
        return tasks.get(taskId);
    }

    @Override
    public Epic getEpicById(UUID epicId) {
        historyManager.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public Task getSubTaskById(UUID subTaskId, UUID epicId) {
        var subTasksOfEpic = epics.get(epicId).getSubTasks();
        for (Task subTask : subTasksOfEpic.values()) {
            if (subTask.getId() == subTaskId) {
                historyManager.add(subTasksOfEpic.get(subTaskId));
                return subTask;
            }
        }
        return null;
    }

    @Override
    public void createTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        this.defineEpicStatus(epic);
    }

    @Override
    public void createSubtask(UUID uuid, SubTask subtask) {
        var s = epics.get(uuid).getSubTasks();
        s.put(subtask.getId(), subtask);
        this.defineEpicStatus(epics.get(uuid));
    }

    @Override
    public void updateTask(Task task) {
        tasks.replace(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        tasks.replace(epic.getId(), epic);
        this.defineEpicStatus(epic);
    }

    @Override
    public void deleteTaskById(UUID uuid) {
        tasks.remove(uuid);
    }

    @Override
    public void deleteEpicById(UUID uuid) {
        epics.remove(uuid);
    }

    @Override
    public ArrayList<Task> getAllSubtasksOfEpic(UUID uuid) {
        var subTasksOfEpic = epics.get(uuid).getSubTasks();
        return new ArrayList<>(subTasksOfEpic.values());
    }

    private void defineEpicStatus(@NotNull Epic epic) {
        int flagN = 0;
        int flagI = 0;
        int flagD = 0;

        if (epic.getSubTasks().size() == 0) {
            epic.setStatus(Status.NEW);
            return;
        }
        for (Task subTask : epic.getSubTasks().values()) {
            if (subTask.getStatus().equals(Status.IN_PROGRESS)) {
                flagI++;
            }
            if (subTask.getStatus().equals(Status.NEW)) {
                flagN++;
            }
            if (subTask.getStatus().equals(Status.DONE)) {
                flagD++;
            }
        }
        if (flagI == 0 && flagD == 0) {
            epic.setStatus(Status.NEW);
        }
        if (flagN == 0 && flagI == 0) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}
