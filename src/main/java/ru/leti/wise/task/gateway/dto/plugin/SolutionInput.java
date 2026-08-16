package ru.leti.wise.task.gateway.dto.plugin;

public record SolutionInput(
        String pluginId,
        PluginType pluginType,
        Payload payload,
        AdditionalPayload additionalPayload
) {}