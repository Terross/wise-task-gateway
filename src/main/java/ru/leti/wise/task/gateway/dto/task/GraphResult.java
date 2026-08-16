package ru.leti.wise.task.gateway.dto.task;

public record GraphResult(
        String id,
        double originalTimeResult,
        double timeResult,
        String originalResult,
        String result
) {}