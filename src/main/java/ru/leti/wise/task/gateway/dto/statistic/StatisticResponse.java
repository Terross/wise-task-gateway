package ru.leti.wise.task.gateway.dto.statistic;

public record StatisticResponse(
        StatisticScope scope,
        StatisticType type,
        String updated_at,
        String task_id,
        String user_id,
        double value,
        String event_type
) {}