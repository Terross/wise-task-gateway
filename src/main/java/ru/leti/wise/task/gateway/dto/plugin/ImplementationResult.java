package ru.leti.wise.task.gateway.dto.plugin;

import java.util.List;

public record ImplementationResult(
        boolean result,
        List<GraphTestResult> graphTestResults
) {}