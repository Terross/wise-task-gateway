package ru.leti.wise.task.gateway.dto.statistic;

public record StatisticRequestInput(
        StatisticType type,
        StatisticScope scope,
        String event_type,
        String task_id,
        String user_id
) {}