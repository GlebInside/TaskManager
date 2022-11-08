package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Epic extends Task {

    public Epic(String name, String description, UUID id) {
        super(name, description, id);
    }

    private HashMap<UUID, Task> subTasks = new HashMap<>();

    public HashMap<UUID, Task> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name=" + getName() +
                ", description=" + getDescription() +
                ", subTasks=" + subTasks +
                ", status=" + getStatus() +
                '}';
    }
}
