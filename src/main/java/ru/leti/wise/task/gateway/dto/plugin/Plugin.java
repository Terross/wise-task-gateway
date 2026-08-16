package ru.leti.wise.task.gateway.dto.plugin;

public record Plugin(
        String id,
        String name,
        String description,
        String category,
        String jarName,
        String jarFile,
        String authorId,
        GraphType graphType,
        boolean isValid,
        String beanName,
        PluginType pluginType,
        boolean isInternal
) {}