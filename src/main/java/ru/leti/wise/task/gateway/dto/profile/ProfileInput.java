package ru.leti.wise.task.gateway.dto.profile;

public record ProfileInput(
        String id,
        String email,
        String profilePassword,
        String firstName,
        String lastName,
        String patronymic,
        Role profileRole
) {}