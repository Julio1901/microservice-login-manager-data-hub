package com.julio.loginmanager.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record LoginDto (@Nullable @Schema(nullable = true) UUID loginId,
                       @NotBlank String serviceName,
                       @NotBlank String password,
                       @Nullable  @Schema(nullable = true)String webSiteLink,
                       @NotBlank String description,
                       @Nullable @Schema(nullable = true) Integer payDay,
                       @NotBlank String owner){
}
