package ru.leti.wise.task.gateway.dto.graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {

    GRAY("GRAY"),
    RED("RED"),
    BLUE("BLUE"),
    GREEN("GREEN");
    private final String value;
}