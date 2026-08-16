package ru.leti.wise.task.gateway.dto.statistic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatisticScope {
    USER("USER"),
    TASK("TASK"),
    SESSION("SESSION"),
    GLOBAL("GLOBAL");

    private final String value;
}