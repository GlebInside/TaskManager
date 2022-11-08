package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FileBackedTasksManager extends InMemoryTaskManager {

    private static final String columns = "id,type,name,status,description,epic";

    public void save() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/save"), StandardCharsets.UTF_8))) {
            writer.write(columns);
            writer.write("\r\n");
            for (Epic epic : epics.values()) {
                writeObject(writer, epic, "Epic", "");
                for (Task subTask : epic.getSubTasks().values()) {
                    writeObject(writer, subTask, "subTask", epic.getId().toString());
                }
            }
            for (Task task : tasks.values()) {
                writeObject(writer, task, "Task", "");
            }
        } catch (IOException ex) {
            System.out.println("Bad writing");
        }
    }

    private void writeObject(Writer writer, Task task, String type, String epicId) throws IOException {
        writer.write(task.getId().toString());
        writer.write(",");
        writer.write(type);
        writer.write(",");
        writer.write(task.getName());
        writer.write(",");
        writer.write(task.getStatus().toString());
        writer.write(",");
        writer.write(task.getDescription());
        writer.write(",");
        writer.write(epicId);
        writer.write("\r\n");
    }

    public static FileBackedTasksManager readObject(String str) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(str), StandardCharsets.UTF_8))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                processLine(line, fileBackedTasksManager);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileBackedTasksManager;
    }

    private static void processLine(String line, FileBackedTasksManager fileBackedTasksManager) {
        var parts = line.split(",");
        var id = UUID.fromString(parts[0]);
        var type = parts[1];
        var name = parts[2];
        var status = Status.valueOf(parts[3]);
        var description = parts[4];
        UUID epicId = null;
        if (parts.length == 6) {
            epicId = UUID.fromString(parts[5]);
        }
        switch (type) {
            case "Task" -> fileBackedTasksManager.createTask(new Task(name, description, id, status));
            case "Epic" -> fileBackedTasksManager.createEpic(new Epic(name, description, id));
            case "subTask" -> {
                var subTask = new SubTask(name, description, id, status);
                fileBackedTasksManager.createSubtask(epicId, subTask);
            }
        }
    }

    @Override
    public Task getTaskById(UUID taskId) {
        var a = super.getTaskById(taskId);
        save();
        return a;
    }

    @Override
    public Epic getEpicById(UUID epicId) {
        var a = super.getEpicById(epicId);
        save();
        return a;
    }

    @Override
    public Task getSubTaskById(UUID subTaskId, UUID epicId) {
        var a = super.getSubTaskById(subTaskId, epicId);
        save();
        return a;
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubtask(UUID uuid, SubTask subtask) {
        super.createSubtask(uuid, subtask);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }
}
