package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SubTask extends Task {
    public SubTask(String name, String description, UUID id, Status status) {
        super(name, description, id, status);
    }
}
