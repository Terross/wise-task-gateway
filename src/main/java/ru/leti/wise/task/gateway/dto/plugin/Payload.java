package ru.leti.wise.task.gateway.dto.plugin;

import ru.leti.wise.task.gateway.dto.graph.GraphInput;

public record Payload(
        PayloadType discriminator,
        GraphInput graph
) {}