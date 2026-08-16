package ru.leti.wise.task.gateway.dto.profile;

public record ResetPasswordRequest(
        String recoveryToken,
        String newPassword
) {}