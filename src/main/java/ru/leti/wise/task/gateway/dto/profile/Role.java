package ru.leti.wise.task.gateway.dto.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    AUTHOR("AUTHOR"),
    ADMIN("ADMIN");

    private final String value;
}