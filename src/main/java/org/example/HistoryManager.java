package org.example;

import java.util.ArrayList;

public interface HistoryManager {

    Object add(Task task);

    ArrayList<Task> getHistory();
}
