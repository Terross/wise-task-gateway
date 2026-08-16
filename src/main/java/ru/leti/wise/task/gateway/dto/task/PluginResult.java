package ru.leti.wise.task.gateway.dto.task;

public record PluginResult(
        String pluginId,
        boolean isCorrect,
        String value,
        String trueValue,
        String pluginMessage
) {}