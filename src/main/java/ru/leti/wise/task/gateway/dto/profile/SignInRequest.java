package ru.leti.wise.task.gateway.dto.profile;

public record SignInRequest(
        String email,
        String password
) {}