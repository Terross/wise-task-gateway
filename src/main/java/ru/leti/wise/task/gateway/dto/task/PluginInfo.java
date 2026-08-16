package ru.leti.wise.task.gateway.dto.task;

import ru.leti.wise.task.gateway.dto.plugin.PluginType;

public record PluginInfo(
        String pluginId,
        String value,
        String mistakeText,
        String sign,
        PluginType pluginType
) {}