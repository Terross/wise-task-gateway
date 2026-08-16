package ru.leti.wise.task.gateway.dto.plugin;

public record GraphTestResult(
        String graphId,
        int originalTimeResult,
        int timeResult,
        String result,
        String originalResult
) {}