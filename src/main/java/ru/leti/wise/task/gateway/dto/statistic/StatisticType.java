package ru.leti.wise.task.gateway.dto.statistic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatisticType {
    SUCCESS_RATE("SUCCESS_RATE"),
    SUM("SUM"),
    MEAN("MEAN");

    private final String value;
}