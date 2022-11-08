package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InMemoryHistoryManager implements HistoryManager {

    ArrayList<Task> views = new ArrayList<>();
    Map<UUID, Task> map = new LinkedHashMap<>();

    @Override
    public Task add(Task task) {
        return map.put(task.getId(), task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        views.addAll(map.values());
        return views;
    }
}
