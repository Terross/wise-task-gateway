package ru.leti.wise.task.gateway.dto.plugin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GraphType {
    DIRECT("DIRECT"),
    UNDIRECT("UNDIRECT"),
    ANY("ANY");

    private final String value;
}