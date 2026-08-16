package ru.leti.wise.task.gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskType {
    GRAPH("GRAPH"),
    IMPLEMENTATION("IMPLEMENTATION"),
    UNRECOGNIZED("UNRECOGNIZED");

    private final String value;
}