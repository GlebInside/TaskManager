package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Data {
    private String name;
    private String description;
    private UUID id;
    private Status status;
}
