package com.julio.loginmanager.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record GetByServiceIdDto(@NotBlank UUID loginId) {
}
