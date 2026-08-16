package ru.leti.wise.task.gateway.dto.plugin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayloadType {
    GRAPH("GRAPH");

    private final String value;
}