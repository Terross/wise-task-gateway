package ru.leti.wise.task.gateway.dto.plugin;

import java.util.List;

public record PluginInput(
        String id,
        String name,
        String description,
        String category,
        String jarName,
        String jarFile,
        String authorId,
        boolean isValid,
        GraphType graphType,
        String beanName,
        PluginType pluginType,
        boolean isInternal
) {}